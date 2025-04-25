package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.domain.exception.WebClientException;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.supplier.TokenSupplier;
import io.github.cnadjim.football.data.client.spi.HttpConnector;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import static io.github.cnadjim.football.data.client.domain.Constants.*;

@DomainService
public class WebClientService {

    private final TokenSupplier tokenSupplier;
    private final HttpConnector httpConnector;

    public WebClientService(TokenSupplier tokenSupplier, HttpConnector httpConnector) {
        this.tokenSupplier = tokenSupplier;
        this.httpConnector = httpConnector;
    }

    public <RESPONSE_TYPE> Optional<RESPONSE_TYPE> GET(String path, Class<RESPONSE_TYPE> responseType) throws WebClientException {

        final String token = tokenSupplier.getToken();

        final String baseURI = UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .port(PORT)
                .path(API_VERSION_4)
                .build()
                .toString();

        final URI uri = URI.create(baseURI + path);

        final HashMap<String, String> headers = new HashMap<>();

        headers.put(ACCEPT_HEADER, APPLICATION_JSON);
        headers.put(AUTHENTICATION_HEADER, token);

        try {
            final ClientResponse<RESPONSE_TYPE> response = httpConnector.send(uri, headers, responseType);

            if (response.isSuccess()) {
                return Optional.ofNullable(response.data());
            } else if (response.error().error() == 404) {
                return Optional.empty();
            } else {
                throw new WebClientException(response.error().message());
            }

        } catch (WebClientException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new WebClientException(exception);
        }
    }
}
