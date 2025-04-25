package io.github.cnadjim.football.data.client.api.competition;

import io.github.cnadjim.football.data.client.annotation.UseCase;
import io.github.cnadjim.football.data.client.domain.model.Competition;
import io.github.cnadjim.football.data.client.domain.model.Match;
import io.github.cnadjim.football.data.client.domain.model.Standing;
import io.github.cnadjim.football.data.client.domain.model.Team;

import java.time.Year;
import java.util.Collection;
import java.util.Optional;

@UseCase
public interface CompetitionUseCase {

    Collection<Competition> findAll();

    Optional<Competition> findByCompetitionId(long competitionId);

    Collection<Standing> findStandingsByCompetitionIdAndSeasonYear(long competitionId, Year seasonYear);

    Collection<Match> findMatchesByCompetitionIdAndSeasonYear(long competitionId, Year seasonYear);

    Collection<Team> findTeamsByCompetitionIdAndSeasonYear(long competitionId, Year seasonYear);

    default Collection<Match> findMatchesByCompetitionId(long competitionId) {
        return findMatchesByCompetitionIdAndSeasonYear(competitionId, Year.now().minusYears(1));
    }

    default Collection<Standing> findStandingsByCompetitionId(long competitionId) {
        return findStandingsByCompetitionIdAndSeasonYear(competitionId, Year.now().minusYears(1));
    }

    default Collection<Team> findTeamsByCompetitionId(long competitionId) {
        return findTeamsByCompetitionIdAndSeasonYear(competitionId, Year.now().minusYears(1));
    }
}
