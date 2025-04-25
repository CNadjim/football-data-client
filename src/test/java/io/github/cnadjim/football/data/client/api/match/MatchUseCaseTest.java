package io.github.cnadjim.football.data.client.api.match;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.exception.WebClientException;
import io.github.cnadjim.football.data.client.domain.model.Match;
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
public class MatchUseCaseTest extends UseCaseTest {

    private Match match;

    @BeforeEach
    public void setUp() {
        match = readFile("sample/match.json", Match.class);
    }

    @Test
    public void findByMatchId_should_work() {
        // given
        doReturn(ClientResponse.success(match))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Match.class));

        // when
        final Optional<Match> optionalMatch = footballDataHttpClient.match().findByMatchId(497958);

        // then
        Assertions.assertNotNull(optionalMatch);
        Assertions.assertTrue(optionalMatch.isPresent());
        Assertions.assertEquals(497958L, optionalMatch.get().id());
    }

    @Test
    public void findByMatchId_should_not_throw_but_return_empty_optional() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Error", 404)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Match.class));

        // when
        final Optional<Match> optionalMatch = footballDataHttpClient.match().findByMatchId(497958);

        // then
        Assertions.assertNotNull(optionalMatch);
        Assertions.assertTrue(optionalMatch.isEmpty());
    }

    @Test
    public void findByMatchId_should_throw_web_client_exception() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Bad request", 400)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Match.class));

        // when
        final WebClientException webClientException = Assertions.assertThrows(WebClientException.class, () -> footballDataHttpClient.match().findByMatchId(497958));

        // then
        Assertions.assertEquals("Bad request", webClientException.getMessage());
    }
}
