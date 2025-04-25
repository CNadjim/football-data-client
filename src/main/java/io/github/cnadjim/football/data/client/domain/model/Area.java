package io.github.cnadjim.football.data.client.domain.model;


import java.util.Collection;

public record Area(Long id,
                   String name,
                   String code,
                   String countryCode,
                   String flag,
                   Long parentAreaId,
                   String parentArea,
                   Collection<Area> childAreas) {

}
