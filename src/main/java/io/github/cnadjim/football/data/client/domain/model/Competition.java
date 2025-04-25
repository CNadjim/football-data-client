package io.github.cnadjim.football.data.client.domain.model;


import java.time.LocalDateTime;
import java.util.Collection;

public record Competition(Long id,
                          Area area,
                          String name,
                          String code,
                          CompetitionType type,
                          String emblem,
                          Season currentSeason,
                          CompetitionPlan plan,
                          Integer numberOfAvailableSeasons,
                          Collection<Season> seasons,
                          LocalDateTime lastUpdated) {
}
