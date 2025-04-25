package io.github.cnadjim.football.data.client.api;

import io.github.cnadjim.football.data.client.FootballDataClient;
import io.github.cnadjim.football.data.client.infra.InMemoryTokenRegistry;
import io.github.cnadjim.football.data.client.spi.TokenRegistry;

public abstract class UseCaseTest {

    private final String token = System.getenv().getOrDefault("FOOTBALL_DATA_TOKEN", null);

    private final TokenRegistry tokenRegistry = InMemoryTokenRegistry.of(token);

    protected final FootballDataClient footballDataHttpClient = new FootballDataClient.FootballDataClientBuilder()
            .tokenRegistry(tokenRegistry)
            .build();
}
