package io.github.cnadjim.football.data.client.domain.model;

public record StandingTableItem(Integer position,
                                Team team,
                                Integer playedGames,
                                String form,
                                Integer won,
                                Integer draw,
                                Integer lost,
                                Integer points,
                                Integer goalsFor,
                                Integer goalsAgainst,
                                Integer goalDifference) {
}
