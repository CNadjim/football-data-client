package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.api.team.TeamUseCase;
import io.github.cnadjim.football.data.client.domain.model.Team;

import java.util.Optional;

@DomainService
public class TeamService implements TeamUseCase {

    private final WebClientService webClientService;

    public TeamService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public Optional<Team> findByTeamId(long teamId) {
        return webClientService.GET(String.format("/teams/%s", teamId), Team.class);
    }
}
