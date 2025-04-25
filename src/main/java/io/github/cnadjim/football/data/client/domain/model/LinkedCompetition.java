package io.github.cnadjim.football.data.client.domain.model;



import java.time.LocalDateTime;

public record LinkedCompetition(Long id,
                                String name,
                                String code,
                                CompetitionType type,
                                String emblem,
                                LocalDateTime lastUpdated) {
}
