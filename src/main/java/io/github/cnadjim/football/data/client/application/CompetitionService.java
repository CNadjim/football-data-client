package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.api.competition.CompetitionUseCase;
import io.github.cnadjim.football.data.client.domain.model.Competition;
import io.github.cnadjim.football.data.client.domain.model.Match;
import io.github.cnadjim.football.data.client.domain.model.Standing;
import io.github.cnadjim.football.data.client.domain.model.Team;
import io.github.cnadjim.football.data.client.domain.response.CompetitionsResponse;
import io.github.cnadjim.football.data.client.domain.response.MatchesResponse;
import io.github.cnadjim.football.data.client.domain.response.StandingsResponse;
import io.github.cnadjim.football.data.client.domain.response.TeamsResponse;

import java.time.Year;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@DomainService
public class CompetitionService implements CompetitionUseCase {

    private final WebClientService webClientService;

    public CompetitionService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public Collection<Competition> findAll() {
        return webClientService.GET("/competitions", CompetitionsResponse.class)
                .map(CompetitionsResponse::competitions)
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<Competition> findByCompetitionId(long competitionId) {
        return webClientService.GET("/competitions/" + competitionId, Competition.class);
    }


    @Override
    public Collection<Standing> findStandingsByCompetitionIdAndSeasonYear(long competitionId, Year seasonYear) {
        return webClientService.GET("/competitions/" + competitionId + "/standings?season=" + seasonYear.getValue(), StandingsResponse.class)
                .map(StandingsResponse::standings)
                .orElse(Collections.emptyList());
    }

    @Override
    public Collection<Match> findMatchesByCompetitionIdAndSeasonYear(long competitionId, Year seasonYear) {
        return webClientService.GET("/competitions/" + competitionId + "/matches?season=" + seasonYear.getValue(), MatchesResponse.class)
                .map(MatchesResponse::matches)
                .orElse(Collections.emptyList());
    }

    @Override
    public Collection<Team> findTeamsByCompetitionIdAndSeasonYear(long competitionId, Year seasonYear) {
        return webClientService.GET("/competitions/" + competitionId + "/teams?season=" + seasonYear.getValue(), TeamsResponse.class)
                .map(TeamsResponse::teams)
                .orElse(Collections.emptyList());
    }
}
