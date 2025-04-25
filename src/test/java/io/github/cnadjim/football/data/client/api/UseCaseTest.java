package io.github.cnadjim.football.data.client.api;

import io.github.cnadjim.football.data.client.FootballDataClient;
import io.github.cnadjim.football.data.client.spi.HttpConnector;
import org.mockito.Mockito;

public abstract class UseCaseTest {

    protected final HttpConnector httpConnector = Mockito.mock(HttpConnector.class);

    protected final FootballDataClient footballDataHttpClient = new FootballDataClient.FootballDataClientBuilder()
            .httpConnector(httpConnector)
            .addToken("token1")
            .build();
}
