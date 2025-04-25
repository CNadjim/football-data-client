package io.github.cnadjim.football.data.client.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.cnadjim.football.data.client.annotation.Stub;
import io.github.cnadjim.football.data.client.domain.exception.ConversionException;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Stub
public class JacksonBoyHandler<RESPONSE_TYPE> implements HttpResponse.BodyHandler<ClientResponse<RESPONSE_TYPE>> {

    private final Class<RESPONSE_TYPE> responseClass;

    private final ObjectMapper objectMapper = buildObjectMapper();


    public JacksonBoyHandler(Class<RESPONSE_TYPE> responseClass) {
        this.responseClass = responseClass;
    }

    public static <RESPONSE_TYPE> HttpResponse.BodyHandler<ClientResponse<RESPONSE_TYPE>> of(Class<RESPONSE_TYPE> responseClass) {
        return new JacksonBoyHandler<>(responseClass);
    }

    @Override
    public HttpResponse.BodySubscriber<ClientResponse<RESPONSE_TYPE>> apply(HttpResponse.ResponseInfo responseInfo) {
        final int statusCode = responseInfo.statusCode();
        final Function<String, ClientResponse<RESPONSE_TYPE>> mapper;

        if (Integer.parseInt(Integer.toString(statusCode).substring(0, 1)) == 2) {
            mapper = this::parseSuccess;
        } else {
            mapper = this::parseError;
        }

        return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8), mapper);
    }

    private ClientResponse<RESPONSE_TYPE> parseSuccess(String input) {
        RESPONSE_TYPE response = parseJson(input, responseClass);
        return ClientResponse.success(response);
    }

    private ClientResponse<RESPONSE_TYPE> parseError(String input) {
        ErrorResponse response = parseJson(input, ErrorResponse.class);
        return ClientResponse.error(response);
    }

    private <R> R parseJson(String input, Class<R> clazz) {
        try {
            return objectMapper.readValue(input, clazz);
        } catch (Exception exception) {
            throw new ConversionException(clazz, exception);
        }
    }

    public static ObjectMapper buildObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(FAIL_ON_IGNORED_PROPERTIES);
        return objectMapper;
    }
}
