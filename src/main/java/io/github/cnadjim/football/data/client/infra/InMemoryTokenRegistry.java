package io.github.cnadjim.football.data.client.infra;

import io.github.cnadjim.football.data.client.annotation.Stub;
import io.github.cnadjim.football.data.client.domain.exception.TokensMissingException;
import io.github.cnadjim.football.data.client.spi.TokenRegistry;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import static java.util.Objects.isNull;

@Stub
public record InMemoryTokenRegistry(CopyOnWriteArraySet<String> tokens) implements TokenRegistry{

    public InMemoryTokenRegistry {
        if (isNull(tokens) || tokens.isEmpty()) throw new TokensMissingException();
    }

    public InMemoryTokenRegistry() {
        this(new CopyOnWriteArraySet<>());
    }

    public InMemoryTokenRegistry(Collection<String> tokens) {
        this(new CopyOnWriteArraySet<>(tokens));
    }

    public static InMemoryTokenRegistry of(String... tokens) {
        return new InMemoryTokenRegistry(List.of(tokens));
    }

    public static InMemoryTokenRegistry of(String token) {
        return new InMemoryTokenRegistry(Collections.singleton(token));
    }

    @Override
    public Collection<String> findAll() {
        return tokens;
    }
}
