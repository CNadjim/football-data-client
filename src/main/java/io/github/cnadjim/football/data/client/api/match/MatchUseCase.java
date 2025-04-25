package io.github.cnadjim.football.data.client.api.match;

import io.github.cnadjim.football.data.client.annotation.UseCase;
import io.github.cnadjim.football.data.client.domain.model.Match;

import java.util.Optional;

@UseCase
public interface MatchUseCase {

    Optional<Match> findByMatchId(long matchId);
}
