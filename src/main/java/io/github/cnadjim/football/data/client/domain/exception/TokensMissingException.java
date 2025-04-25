package io.github.cnadjim.football.data.client.domain.exception;

public class TokensMissingException extends RuntimeException {

    public TokensMissingException() {
        super("tokens must not be null or empty");
    }
}
