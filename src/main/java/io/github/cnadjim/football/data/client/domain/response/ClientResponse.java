package io.github.cnadjim.football.data.client.domain.response;

import static java.util.Objects.isNull;

public record ClientResponse<EXPECTED_RESPONSE>(EXPECTED_RESPONSE data, ErrorResponse error) {

    public boolean isSuccess() {
        return isNull(error);
    }

    public static <EXPECTED_RESPONSE> ClientResponse<EXPECTED_RESPONSE> success(EXPECTED_RESPONSE data) {
        return new ClientResponse<>(data, null);
    }

    public static <EXPECTED_RESPONSE> ClientResponse<EXPECTED_RESPONSE> error(ErrorResponse error) {
        return new ClientResponse<>(null, error);
    }
}
