package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.api.area.AreaUseCase;
import io.github.cnadjim.football.data.client.domain.model.Area;
import io.github.cnadjim.football.data.client.domain.response.AreasResponse;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@DomainService
public class AreaService implements AreaUseCase {

    private final WebClientService webClientService;

    public AreaService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public Collection<Area> findAll() {
        return webClientService.GET("/areas", AreasResponse.class)
                .map(AreasResponse::areas)
                .orElseGet(Collections::emptyList);
    }

    @Override
    public Optional<Area> findByAreaId(long areaId) {
        return webClientService.GET(String.format("/areas/%s", areaId), Area.class);
    }
}
