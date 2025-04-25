package io.github.cnadjim.football.data.client.api.area;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.exception.WebClientException;
import io.github.cnadjim.football.data.client.domain.model.Area;
import io.github.cnadjim.football.data.client.domain.response.AreasResponse;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Optional;

import static io.github.cnadjim.football.data.client.utils.FileUtils.readFile;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class AreaUseCaseTest extends UseCaseTest {

    private Area area;
    private AreasResponse areasResponse;

    @BeforeEach
    public void setUp() {
        area = readFile("sample/area.json", Area.class);
        areasResponse = readFile("sample/areas.json", AreasResponse.class);
    }

    @Test
    public void findAll_should_work() {
        // given
        doReturn(ClientResponse.success(areasResponse))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(AreasResponse.class));

        // when
        final Collection<Area> areas = footballDataHttpClient.area().findAll();

        // then
        Assertions.assertNotNull(areas);
        Assertions.assertFalse(areas.isEmpty());
        Assertions.assertEquals(272, areas.size());
    }

    @Test
    public void findByAreaId_should_work() {
        // given
        doReturn(ClientResponse.success(area))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Area.class));

        // when
        final Optional<Area> optionalArea = footballDataHttpClient.area().findByAreaId(2077);

        // then
        Assertions.assertNotNull(optionalArea);
        Assertions.assertTrue(optionalArea.isPresent());
        Assertions.assertEquals(2077, optionalArea.get().id());
    }

    @Test
    public void findByAreaId_should_not_throw_but_return_empty_optional() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Error", 404)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Area.class));

        // when
        final Optional<Area> optionalArea = footballDataHttpClient.area().findByAreaId(1L);

        // then
        Assertions.assertNotNull(optionalArea);
        Assertions.assertTrue(optionalArea.isEmpty());
    }

    @Test
    public void findByAreaId_should_throw_web_client_exception() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Bad request", 400)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Area.class));

        // when
        final WebClientException webClientException = Assertions.assertThrows(WebClientException.class, () -> footballDataHttpClient.area().findByAreaId(1L));
        Assertions.assertEquals("Bad request", webClientException.getMessage());
    }
}
