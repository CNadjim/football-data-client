package io.github.cnadjim.football.data.client.domain.model;

public record Goal(Integer minute,
                   Integer injuryTime,
                   GoalType type,
                   LinkedTeam team,
                   LinkedPlayer scorer,
                   LinkedPlayer assist,
                   GoalScore score) {
}
