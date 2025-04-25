package io.github.cnadjim.football.data.client.api.match;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.model.Match;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.github.cnadjim.football.data.client.domain.model.ScoreWinner.AWAY_TEAM;

public class MatchUseCaseTest extends UseCaseTest {

    @Test
    public void findByMatchId_should_work() {
        final Optional<Match> optionalMatch = footballDataHttpClient.match().findByMatchId(497958);
        Assertions.assertNotNull(optionalMatch);
        Assertions.assertTrue(optionalMatch.isPresent());
        Assertions.assertTrue(optionalMatch.get().score().winner().equals(AWAY_TEAM));
    }

}
