package io.github.cnadjim.football.data.client.spi;

import io.github.cnadjim.football.data.client.domain.exception.TokensMissingException;

import java.util.Collection;
import java.util.Iterator;

import static java.util.Objects.isNull;

public interface TokenRegistry {

    void addToken(String token);

    Collection<String> findAll();

    default Iterator<String> iterator() {
        final Collection<String> tokens = findAll();

        if (isNull(tokens) || tokens.isEmpty()) {
            throw new TokensMissingException();
        }

        return tokens.iterator();
    }

}
