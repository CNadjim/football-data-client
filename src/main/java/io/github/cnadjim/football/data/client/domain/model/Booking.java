package io.github.cnadjim.football.data.client.domain.model;

public record Booking(Integer minute,
                      LinkedTeam team,
                      LinkedPlayer player,
                      CardType card) {
}
