package io.github.cnadjim.football.data.client.api.team;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TeamUseCaseTest extends UseCaseTest {
    @Test
    public void findByTeamId_should_work() {
        final Optional<Team> optionalTeam = footballDataHttpClient.team().findByTeamId(511);
        Assertions.assertNotNull(optionalTeam);
        Assertions.assertTrue(optionalTeam.isPresent());
        Assertions.assertTrue(optionalTeam.get().name().equals("Toulouse FC"));
    }
}
