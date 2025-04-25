package io.github.cnadjim.football.data.client.api.area;

import io.github.cnadjim.football.data.client.FootballDataClient;
import io.github.cnadjim.football.data.client.domain.model.Area;
import io.github.cnadjim.football.data.client.domain.response.AreasResponse;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import io.github.cnadjim.football.data.client.infra.InMemoryTokenRegistry;
import io.github.cnadjim.football.data.client.spi.HttpConnector;
import io.github.cnadjim.football.data.client.spi.TokenRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class MockFindAreaTest {

    private final HttpConnector httpConnector = Mockito.mock(HttpConnector.class);
    private final TokenRegistry tokenRegistry = InMemoryTokenRegistry.of("token1", "token2");

    private final FootballDataClient footballDataHttpClient = new FootballDataClient.FootballDataClientBuilder()
            .httpConnector(httpConnector)
            .tokenRegistry(tokenRegistry)
            .build();

    @Test
    public void areas_should_work() throws Exception {
        // given
        final Area testArea = new Area(1L, "Test Area", "TST", null, 2L, "Parent Area", null, null);
        final AreasResponse areasResponse = new AreasResponse(singletonList(testArea));
        doReturn(ClientResponse.success(areasResponse))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(AreasResponse.class));

        // when
        final Collection<Area> areas = footballDataHttpClient.area().findAll();

        Assertions.assertNotNull(areas);
        Assertions.assertFalse(areas.isEmpty());
    }

    @Test
    public void area_should_work() throws Exception {
        // given
        final Area testArea = new Area(1L, "Test Area", "TST", null, 2L, "Parent Area", null, null);

        doReturn(ClientResponse.success(testArea))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Area.class));

        // when
        final Optional<Area> optionalArea = footballDataHttpClient.area().findByAreaId(1L);

        Assertions.assertNotNull(optionalArea);
        Assertions.assertTrue(optionalArea.isPresent());
    }

    @Test
    public void area_should_not_throw_but_return_empty_optional() throws Exception {
        // given
        final Area testArea = new Area(1L, "Test Area", "TST", null, 2L, "Parent Area", null, null);

        doReturn(ClientResponse.error(new ErrorResponse("Error", 404)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Area.class));

        // when
        final Optional<Area> optionalArea = footballDataHttpClient.area().findByAreaId(1L);

        Assertions.assertNotNull(optionalArea);
        Assertions.assertTrue(optionalArea.isEmpty());
    }
}
