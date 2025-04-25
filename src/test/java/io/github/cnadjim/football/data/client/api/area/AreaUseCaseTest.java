package io.github.cnadjim.football.data.client.api.area;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.model.Area;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class AreaUseCaseTest extends UseCaseTest {

    @Test
    public void areas_should_work() {
        final Collection<Area> areas = footballDataHttpClient.area().findAll();
        Assertions.assertNotNull(areas);
        Assertions.assertFalse(areas.isEmpty());
        Assertions.assertEquals(272, areas.size());
    }

    @Test
    public void area_should_work() {
        final Optional<Area> optionalArea = footballDataHttpClient.area().findByAreaId(2081);
        Assertions.assertNotNull(optionalArea);
        Assertions.assertTrue(optionalArea.isPresent());
        Assertions.assertTrue(optionalArea.get().name().equals("France"));
    }

    @Test
    public void area_should_not_throw_but_return_empty_optional() {
        final Optional<Area> optionalArea = footballDataHttpClient.area().findByAreaId(1L);
        Assertions.assertNotNull(optionalArea);
        Assertions.assertTrue(optionalArea.isEmpty());
    }
}
