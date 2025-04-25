package io.github.cnadjim.football.data.client.domain.response;

import io.github.cnadjim.football.data.client.domain.model.Competition;

import java.util.Collection;

public record CompetitionsResponse(Collection<Competition> competitions) {

}
