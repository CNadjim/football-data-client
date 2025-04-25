package io.github.cnadjim.football.data.client.domain.model;





import java.time.LocalDateTime;
import java.util.Collection;

public record Team(Long id,
                   Area area,
                   String name,
                   String shortName,
                   String tla,
                   String crest,
                   String address,
                   String website,
                   Integer founded,
                   String clubColors,
                   String venue,
                   Person coach,
                   Integer marketValue,
                   Collection<Person> squad,
                   Collection<Person> staff,
                   LocalDateTime lastUpdated,
                   Season season,
                   Competition competition,
                   Collection<LinkedCompetition> runningCompetitions) {
}
