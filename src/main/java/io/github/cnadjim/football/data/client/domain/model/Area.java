package io.github.cnadjim.football.data.client.domain.model;



import java.time.LocalDateTime;
import java.util.Collection;

public record Area(Long id,
                   String name,
                   String countryCode,
                   String flag,
                   Long parentAreaId,
                   String parentArea,
                   Collection<Area> childAreas,
                   LocalDateTime lastUpdated) {
}
