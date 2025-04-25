package io.github.cnadjim.football.data.client.domain.model;



import java.time.LocalDateTime;
import java.util.Collection;

public record Season(Long id,
                     String startDate,
                     String endDate,
                     Team winner,
                     Integer currentMatchday,
                     Collection<Stage> stages,
                     Integer competitionIdentifier,
                     LocalDateTime lastUpdated) {
}
