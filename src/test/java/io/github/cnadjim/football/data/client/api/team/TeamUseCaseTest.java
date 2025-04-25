package io.github.cnadjim.football.data.client.api.team;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.exception.WebClientException;
import io.github.cnadjim.football.data.client.domain.model.Team;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static io.github.cnadjim.football.data.client.utils.FileUtils.readFile;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TeamUseCaseTest extends UseCaseTest {

    private Team team;

    @BeforeEach
    public void setUp() {
        team = readFile("sample/team.json", Team.class);
    }

    @Test
    public void findByTeamId_should_work() {
        // given
        doReturn(ClientResponse.success(team))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Team.class));

        // when
        final Optional<Team> optionalTeam = footballDataHttpClient.team().findByTeamId(511);

        // then
        Assertions.assertNotNull(optionalTeam);
        Assertions.assertTrue(optionalTeam.isPresent());
        Assertions.assertEquals("Toulouse FC", optionalTeam.get().name());
    }

    @Test
    public void findByTeamId_should_not_throw_but_return_empty_optional() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Error", 404)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Team.class));

        // when
        final Optional<Team> optionalTeam = footballDataHttpClient.team().findByTeamId(511);

        // then
        Assertions.assertNotNull(optionalTeam);
        Assertions.assertTrue(optionalTeam.isEmpty());
    }

    @Test
    public void findByTeamId_should_throw_web_client_exception() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Bad request", 400)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Team.class));

        // when
        final WebClientException webClientException = Assertions.assertThrows(WebClientException.class, () -> footballDataHttpClient.team().findByTeamId(511));

        // then
        Assertions.assertEquals("Bad request", webClientException.getMessage());
    }
}
