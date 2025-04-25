package io.github.cnadjim.football.data.client.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cnadjim.football.data.client.domain.exception.ConversionException;
import io.github.cnadjim.football.data.client.domain.model.Area;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import io.github.cnadjim.football.data.client.utils.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Flow;

public class JacksonBoyHandlerTest {

    @Test
    public void of_shouldCreateNewInstance() {
        // Given
        Class<Area> responseClass = Area.class;

        // When
        HttpResponse.BodyHandler<ClientResponse<Area>> handler = JacksonBoyHandler.of(responseClass);

        // Then
        Assertions.assertNotNull(handler);
        Assertions.assertInstanceOf(JacksonBoyHandler.class, handler);
    }

    @Test
    public void apply_shouldReturnSuccessResponse_whenStatusCodeIs200() {
        // Given
        JacksonBoyHandler<Area> handler = new JacksonBoyHandler<>(Area.class);
        HttpResponse.ResponseInfo responseInfo = new MockResponseInfo(200);
        String areaJson = FileUtils.readFile("sample/area.json");

        // When
        HttpResponse.BodySubscriber<ClientResponse<Area>> subscriber = handler.apply(responseInfo);
        MockBodyPublisher publisher = new MockBodyPublisher(areaJson);
        publisher.subscribe(subscriber);

        // Then
        ClientResponse<Area> response = subscriber.getBody().toCompletableFuture().join();
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.data());
        Assertions.assertNull(response.error());
        Assertions.assertEquals(2077L, response.data().id());
        Assertions.assertEquals("Europe", response.data().name());
        Assertions.assertEquals("EUR", response.data().code());
    }

    @Test
    public void apply_shouldReturnErrorResponse_whenStatusCodeIs404() {
        // Given
        JacksonBoyHandler<Area> handler = new JacksonBoyHandler<>(Area.class);
        HttpResponse.ResponseInfo responseInfo = new MockResponseInfo(404);
        String errorJson = "{\"message\":\"Resource not found\",\"error\":404}";

        // When
        HttpResponse.BodySubscriber<ClientResponse<Area>> subscriber = handler.apply(responseInfo);
        MockBodyPublisher publisher = new MockBodyPublisher(errorJson);
        publisher.subscribe(subscriber);

        // Then
        ClientResponse<Area> response = subscriber.getBody().toCompletableFuture().join();
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.data());
        Assertions.assertNotNull(response.error());
        Assertions.assertEquals("Resource not found", response.error().message());
        Assertions.assertEquals(404, response.error().error());
    }

    @Test
    public void apply_shouldThrowConversionException_whenJsonIsInvalid() {
        // Given
        JacksonBoyHandler<Area> handler = new JacksonBoyHandler<>(Area.class);
        HttpResponse.ResponseInfo responseInfo = new MockResponseInfo(200);
        String invalidJson = "{invalid json}";

        // When
        HttpResponse.BodySubscriber<ClientResponse<Area>> subscriber = handler.apply(responseInfo);
        MockBodyPublisher publisher = new MockBodyPublisher(invalidJson);

        // Then
        CompletionException exception = Assertions.assertThrows(
            CompletionException.class,
            () -> {
                publisher.subscribe(subscriber);
                subscriber.getBody().toCompletableFuture().join();
            }
        );

        // Verify that the cause is a ConversionException
        Assertions.assertInstanceOf(ConversionException.class, exception.getCause());
        ConversionException conversionException = (ConversionException) exception.getCause();
        Assertions.assertEquals("Error occurred while converting into Area", conversionException.getMessage());
    }

    @Test
    public void buildObjectMapper_shouldConfigureObjectMapper() {
        // When
        ObjectMapper objectMapper = JacksonBoyHandler.buildObjectMapper();

        // Then
        Assertions.assertNotNull(objectMapper);

        // Test JavaTimeModule by serializing and deserializing a date
        try {
            LocalDateTime now = LocalDateTime.now();
            String json = objectMapper.writeValueAsString(now);
            LocalDateTime deserialized = objectMapper.readValue(json, LocalDateTime.class);
            Assertions.assertEquals(now.getYear(), deserialized.getYear());
            Assertions.assertEquals(now.getMonth(), deserialized.getMonth());
            Assertions.assertEquals(now.getDayOfMonth(), deserialized.getDayOfMonth());
        } catch (Exception e) {
            Assertions.fail("Failed to serialize/deserialize LocalDateTime: " + e.getMessage());
        }
    }

    // Mock classes for testing
    private static class MockResponseInfo implements HttpResponse.ResponseInfo {
        private final int statusCode;

        public MockResponseInfo(int statusCode) {
            this.statusCode = statusCode;
        }

        @Override
        public int statusCode() {
            return statusCode;
        }

        @Override
        public HttpClient.Version version() {
            return HttpClient.Version.HTTP_1_1;
        }

        @Override
        public HttpHeaders headers() {
            return HttpHeaders.of(Map.of(), (a, b) -> true);
        }
    }

    private record MockBodyPublisher(String content) implements Flow.Publisher<List<ByteBuffer>> {

        @Override
            public void subscribe(Flow.Subscriber<? super List<ByteBuffer>> subscriber) {
                subscriber.onSubscribe(new MockSubscription(subscriber, content));
            }
        }

    private static class MockSubscription implements Flow.Subscription {
        private final Flow.Subscriber<? super List<ByteBuffer>> subscriber;
        private final String content;
        private boolean completed = false;

        public MockSubscription(Flow.Subscriber<? super List<ByteBuffer>> subscriber, String content) {
            this.subscriber = subscriber;
            this.content = content;
        }

        @Override
        public void request(long n) {
            if (!completed && n > 0) {
                ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
                subscriber.onNext(List.of(buffer));
                subscriber.onComplete();
                completed = true;
            }
        }

        @Override
        public void cancel() {
            // Do nothing
        }
    }
}
