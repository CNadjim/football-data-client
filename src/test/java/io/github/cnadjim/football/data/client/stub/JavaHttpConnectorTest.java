package io.github.cnadjim.football.data.client.stub;

import io.github.cnadjim.football.data.client.domain.model.Area;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

public class JavaHttpConnectorTest {

    @Test
    public void send_shouldThrowIllegalArgumentException_whenUriIsNull() {
        // Given
        HttpClient httpClient = HttpClient.newHttpClient();
        JavaHttpConnector javaHttpConnector = new JavaHttpConnector(httpClient);
        Map<String, String> headers = new HashMap<>();
        Class<String> responseType = String.class;

        // When & Then
        IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> javaHttpConnector.send(null, headers, responseType)
        );

        Assertions.assertEquals("uri and responseType must not be null", exception.getMessage());
    }

    @Test
    public void send_shouldThrowIllegalArgumentException_whenResponseTypeIsNull() {
        // Given
        HttpClient httpClient = HttpClient.newHttpClient();
        JavaHttpConnector javaHttpConnector = new JavaHttpConnector(httpClient);
        URI uri = URI.create("https://example.com");
        Map<String, String> headers = new HashMap<>();

        // When & Then
        IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> javaHttpConnector.send(uri, headers, null)
        );

        Assertions.assertEquals("uri and responseType must not be null", exception.getMessage());
    }

    @Test
    public void send_shouldHandleNullHeaders() {
        // Given
        HttpClient httpClient = HttpClient.newHttpClient();
        JavaHttpConnector javaHttpConnector = new JavaHttpConnector(httpClient);
        URI uri = URI.create("https://example.com/non-existent-endpoint");
        Class<Area> responseType = Area.class;

        // When
        ClientResponse<Area> response = javaHttpConnector.send(uri, null, responseType);

        // Then
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.data());
        Assertions.assertNotNull(response.error());
        Assertions.assertEquals(500, response.error().error());
    }
}
