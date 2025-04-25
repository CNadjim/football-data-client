package io.github.cnadjim.football.data.client.stub;

import io.github.cnadjim.football.data.client.annotation.Stub;
import io.github.cnadjim.football.data.client.spi.TokenRegistry;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArraySet;

@Stub
public record InMemoryTokenRegistry(CopyOnWriteArraySet<String> tokens) implements TokenRegistry {

    public InMemoryTokenRegistry() {
        this(new CopyOnWriteArraySet<>());
    }

    public InMemoryTokenRegistry(Collection<String> tokens) {
        this(new CopyOnWriteArraySet<>(tokens));
    }

    public static InMemoryTokenRegistry of(String token) {
        return new InMemoryTokenRegistry(Collections.singleton(token));
    }

    @Override
    public void addToken(String token) {
        tokens.add(token);
    }

    @Override
    public Collection<String> findAll() {
        return tokens;
    }
}
