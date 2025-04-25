package io.github.cnadjim.football.data.client.api.competition;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.exception.WebClientException;
import io.github.cnadjim.football.data.client.domain.model.Competition;
import io.github.cnadjim.football.data.client.domain.model.Match;
import io.github.cnadjim.football.data.client.domain.model.Standing;
import io.github.cnadjim.football.data.client.domain.model.Team;
import io.github.cnadjim.football.data.client.domain.response.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.Collection;
import java.util.Optional;

import static io.github.cnadjim.football.data.client.utils.FileUtils.readFile;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CompetitionUseCaseTest extends UseCaseTest {

    private Competition competition;
    private TeamsResponse teamsResponse;
    private MatchesResponse matchesResponse;
    private StandingsResponse standingsResponse;
    private CompetitionsResponse competitionsResponse;


    @BeforeEach
    public void setUp() {
        competition = readFile("sample/competition.json", Competition.class);
        competitionsResponse = readFile("sample/competitions.json", CompetitionsResponse.class);
        matchesResponse = readFile("sample/matches.json", MatchesResponse.class);
        teamsResponse = readFile("sample/teams.json", TeamsResponse.class);
        standingsResponse = readFile("sample/standings.json", StandingsResponse.class);
    }

    @Test
    public void findAll_should_work() {
        // given
        doReturn(ClientResponse.success(competitionsResponse))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(CompetitionsResponse.class));

        // when
        final Collection<Competition> competitions = footballDataHttpClient.competition().findAll();

        Assertions.assertNotNull(competitions);
        Assertions.assertFalse(competitions.isEmpty());
        Assertions.assertEquals(184, competitions.size());
    }

    @Test
    public void findByCompetitionId_should_work() {
        // given
        doReturn(ClientResponse.success(competition))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Competition.class));

        // when
        final Optional<Competition> optionalCompetition = footballDataHttpClient.competition().findByCompetitionId(2015);

        Assertions.assertNotNull(optionalCompetition);
        Assertions.assertTrue(optionalCompetition.isPresent());
        Assertions.assertEquals("Premier League", optionalCompetition.get().name());
    }

    @Test
    public void findByCompetitionId_should_not_throw_but_return_empty_optional() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Error", 404)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Competition.class));

        // when
        final Optional<Competition> optionalCompetition = footballDataHttpClient.competition().findByCompetitionId(2015);

        Assertions.assertNotNull(optionalCompetition);
        Assertions.assertTrue(optionalCompetition.isEmpty());
    }

    @Test
    public void findByCompetitionId_should_throw_web_client_exception() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Bad request", 400)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Competition.class));

        // when
        final WebClientException webClientException = Assertions.assertThrows(WebClientException.class, () -> footballDataHttpClient.competition().findByCompetitionId(2015));
        Assertions.assertEquals("Bad request", webClientException.getMessage());
    }

    @Test
    public void findMatchesByCompetitionIdAndSeasonYear_should_work() {
        // given
        doReturn(ClientResponse.success(matchesResponse))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(MatchesResponse.class));

        // when
        final Collection<Match> matches = footballDataHttpClient.competition().findMatchesByCompetitionIdAndSeasonYear(2015, Year.of(2024));

        Assertions.assertNotNull(matches);
        Assertions.assertFalse(matches.isEmpty());
        Assertions.assertEquals(306, matches.size());
    }

    @Test
    public void findTeamsByCompetitionIdAndSeasonYear_should_work() {
        // given
        doReturn(ClientResponse.success(teamsResponse))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(TeamsResponse.class));

        // when
        final Collection<Team> teams = footballDataHttpClient.competition().findTeamsByCompetitionIdAndSeasonYear(2015, Year.of(2024));

        Assertions.assertNotNull(teams);
        Assertions.assertFalse(teams.isEmpty());
        Assertions.assertEquals(18, teams.size());
    }

    @Test
    public void findStandingsByCompetitionIdAndSeasonYear_should_work() {
        // given
        doReturn(ClientResponse.success(standingsResponse))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(StandingsResponse.class));

        // when
        final Collection<Standing> standings = footballDataHttpClient.competition().findStandingsByCompetitionIdAndSeasonYear(2015, Year.of(2024));

        Assertions.assertNotNull(standings);
        Assertions.assertFalse(standings.isEmpty());
        Assertions.assertEquals(1, standings.size());
    }
}
