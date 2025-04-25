package io.github.cnadjim.football.data.client.domain.response;

import io.github.cnadjim.football.data.client.domain.model.Standing;

import java.util.Collection;

public record StandingsResponse(Collection<Standing> standings) {

}
