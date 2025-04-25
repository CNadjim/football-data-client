package io.github.cnadjim.football.data.client.api.team;

import io.github.cnadjim.football.data.client.annotation.UseCase;
import io.github.cnadjim.football.data.client.domain.model.Team;

import java.util.Optional;

@UseCase
public interface TeamUseCase {

    Optional<Team> findByTeamId(long teamId);
}
