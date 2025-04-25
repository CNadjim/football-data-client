package io.github.cnadjim.football.data.client.domain.response;

public record ErrorResponse(String message, int error) {

    public static ErrorResponse internalServerError(String message) {
        return new ErrorResponse(message, 500);
    }
}
