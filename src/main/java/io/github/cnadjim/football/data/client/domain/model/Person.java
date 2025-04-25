package io.github.cnadjim.football.data.client.domain.model;



import java.time.LocalDateTime;

public record Person(Long id,
                     String firstName,
                     String lastName,
                     String name,
                     String dateOfBirth,
                     String nationality,
                     Long shirtNumber,
                     Integer marketValue,
                     Contract contract,
                     LocalDateTime lastUpdated) {
}
