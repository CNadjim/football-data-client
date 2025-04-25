package io.github.cnadjim.football.data.client.api.area;

import io.github.cnadjim.football.data.client.annotation.UseCase;
import io.github.cnadjim.football.data.client.domain.model.Area;

import java.util.Collection;
import java.util.Optional;

@UseCase
public interface AreaUseCase {

    Collection<Area> findAll();

    Optional<Area> findByAreaId(long areaId);
}
