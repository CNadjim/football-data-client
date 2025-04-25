package io.github.cnadjim.football.data.client.domain.model;



import java.time.LocalDateTime;

public record Referee(Long id,
                      String name,
                      RefereeType type,
                      String nationality,
                      LocalDateTime lastUpdated) {
}
