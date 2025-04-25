package io.github.cnadjim.football.data.client.domain.response;

import io.github.cnadjim.football.data.client.domain.model.Team;

import java.util.Collection;

public record TeamsResponse(Collection<Team> teams) {

}
