package io.github.cnadjim.football.data.client.api.competition;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.model.Competition;
import io.github.cnadjim.football.data.client.domain.model.Match;
import io.github.cnadjim.football.data.client.domain.model.Standing;
import io.github.cnadjim.football.data.client.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Collection;
import java.util.Optional;

public class CompetitionUseCaseTest extends UseCaseTest {

    @Test
    public void findAll_should_work() {
        final Collection<Competition> competitions = footballDataHttpClient.competition().findAll();
        Assertions.assertNotNull(competitions);
        Assertions.assertFalse(competitions.isEmpty());
        Assertions.assertEquals(13, competitions.size());
    }

    @Test
    public void findByCompetitionId_should_work() {
        final Optional<Competition> optionalCompetition = footballDataHttpClient.competition().findByCompetitionId(2015);
        Assertions.assertNotNull(optionalCompetition);
        Assertions.assertTrue(optionalCompetition.isPresent());
        Assertions.assertTrue(optionalCompetition.get().name().equals("Ligue 1"));
    }


    @Test
    public void findMatchesByCompetitionId_should_work() {
        final Collection<Match> matches = footballDataHttpClient.competition().findMatchesByCompetitionIdAndSeasonYear(2015, Year.of(2024));
        Assertions.assertNotNull(matches);
        Assertions.assertFalse(matches.isEmpty());
        Assertions.assertEquals(306, matches.size());
    }


    @Test
    public void findTeamsByCompetitionId_should_work() {
        final Collection<Team> teams = footballDataHttpClient.competition().findTeamsByCompetitionIdAndSeasonYear(2015, Year.of(2024));
        Assertions.assertNotNull(teams);
        Assertions.assertFalse(teams.isEmpty());
        Assertions.assertEquals(18, teams.size());
    }

    @Test
    public void findStandingsByCompetitionId_should_work() {
        final Collection<Standing> standings = footballDataHttpClient.competition().findStandingsByCompetitionIdAndSeasonYear(2015, Year.of(2024));
        Assertions.assertNotNull(standings);
        Assertions.assertFalse(standings.isEmpty());
        Assertions.assertEquals(3, standings.size());
    }
}
