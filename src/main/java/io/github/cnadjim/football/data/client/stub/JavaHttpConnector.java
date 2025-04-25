package io.github.cnadjim.football.data.client.stub;

import io.github.cnadjim.football.data.client.annotation.Stub;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import io.github.cnadjim.football.data.client.spi.HttpConnector;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@Stub
public class JavaHttpConnector implements HttpConnector {

    private final HttpClient httpClient;

    public JavaHttpConnector(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public <RESPONSE_TYPE> ClientResponse<RESPONSE_TYPE> send(URI uri, Map<String, String> headers, Class<RESPONSE_TYPE> responseType) {

        if (isNull(uri) || isNull(responseType)) {
            throw new IllegalArgumentException("uri and responseType must not be null");
        }

        try {
            final HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder().uri(uri);

            Optional.ofNullable(headers)
                    .orElse(new HashMap<>())
                    .forEach(httpRequestBuilder::header);

            return httpClient.send(httpRequestBuilder.build(), JacksonBoyHandler.of(responseType)).body();
        } catch (Exception exception) {
            return ClientResponse.error(ErrorResponse.internalServerError(exception.getMessage()));
        }
    }
}
