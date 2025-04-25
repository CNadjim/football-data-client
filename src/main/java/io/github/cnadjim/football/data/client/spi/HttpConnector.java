package io.github.cnadjim.football.data.client.spi;

import io.github.cnadjim.football.data.client.domain.response.ClientResponse;

import java.net.URI;
import java.util.Map;

public interface HttpConnector {

    <RESPONSE_TYPE> ClientResponse<RESPONSE_TYPE> send(URI uri, Map<String, String> headers, Class<RESPONSE_TYPE> responseType);
}
