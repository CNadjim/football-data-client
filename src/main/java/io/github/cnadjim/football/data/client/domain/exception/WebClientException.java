package io.github.cnadjim.football.data.client.domain.exception;

public class WebClientException extends RuntimeException {

    public WebClientException(Throwable cause) {
        super(cause);
    }

    public WebClientException(String message) {
        super(message);
    }
}
