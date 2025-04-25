package io.github.cnadjim.football.data.client.domain.model;

public record Substitution(Integer minute, LinkedTeam team, LinkedPlayer playerOut, LinkedPlayer playerIn) {
}
