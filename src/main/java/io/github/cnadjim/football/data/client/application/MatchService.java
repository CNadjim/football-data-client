package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.api.match.MatchUseCase;
import io.github.cnadjim.football.data.client.domain.model.Area;
import io.github.cnadjim.football.data.client.domain.model.Match;

import java.util.Optional;

@DomainService
public class MatchService implements MatchUseCase {

    private final WebClientService webClientService;

    public MatchService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public Optional<Match> findByMatchId(long matchId) {
        return webClientService.GET(String.format("/matches/%s", matchId), Match.class);
    }
}
