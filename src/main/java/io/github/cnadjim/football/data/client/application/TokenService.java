package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.domain.supplier.TokenSupplier;
import io.github.cnadjim.football.data.client.spi.TokenRegistry;

import java.util.Iterator;

import static java.util.Objects.nonNull;

@DomainService
public class TokenService implements TokenSupplier {

    private Iterator<String> tokenIterator;

    private final TokenRegistry tokenRegistry;

    public TokenService(TokenRegistry tokenRegistry) {
        this.tokenRegistry = tokenRegistry;
    }

    @Override
    public String getToken() {
        if (nonNull(tokenIterator) && tokenIterator.hasNext()) {
            return tokenIterator.next();
        } else {
            tokenIterator = tokenRegistry.iterator();
            return getToken();
        }
    }
}
