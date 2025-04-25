package io.github.cnadjim.football.data.client.domain.response;

import io.github.cnadjim.football.data.client.domain.model.Match;

import java.util.Collection;

public record MatchesResponse(Collection<Match> matches) {

}
