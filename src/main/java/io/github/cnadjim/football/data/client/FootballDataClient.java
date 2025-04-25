package io.github.cnadjim.football.data.client;

import io.github.cnadjim.football.data.client.api.area.AreaUseCase;
import io.github.cnadjim.football.data.client.api.competition.CompetitionUseCase;
import io.github.cnadjim.football.data.client.api.match.MatchUseCase;
import io.github.cnadjim.football.data.client.api.person.PersonUseCase;
import io.github.cnadjim.football.data.client.api.team.TeamUseCase;
import io.github.cnadjim.football.data.client.application.*;
import io.github.cnadjim.football.data.client.spi.HttpConnector;
import io.github.cnadjim.football.data.client.spi.TokenRegistry;
import io.github.cnadjim.football.data.client.stub.InMemoryTokenRegistry;
import io.github.cnadjim.football.data.client.stub.JavaHttpConnector;

import java.net.http.HttpClient;
import java.util.Optional;

public record FootballDataClient(AreaUseCase area,
                                 TeamUseCase team,
                                 MatchUseCase match,
                                 PersonUseCase person,
                                 CompetitionUseCase competition) {

    public static final class FootballDataClientBuilder {
        HttpConnector httpConnector;
        TokenRegistry tokenRegistry = new InMemoryTokenRegistry();

        public FootballDataClientBuilder addToken(String token) {
            this.tokenRegistry.addToken(token);
            return this;
        }

        public FootballDataClientBuilder httpConnector(HttpConnector httpConnector) {
            this.httpConnector = httpConnector;
            return this;
        }

        public FootballDataClient build() {
            final HttpConnector httpConnector = Optional.ofNullable(this.httpConnector).orElseGet(() -> new JavaHttpConnector(HttpClient.newHttpClient()));
            final TokenService tokenService = new TokenService(tokenRegistry);

            final WebClientService webClientService = new WebClientService(tokenService, httpConnector);
            final AreaService areaService = new AreaService(webClientService);
            final TeamService teamService = new TeamService(webClientService);
            final MatchService matchService = new MatchService(webClientService);
            final PersonService personService = new PersonService(webClientService);
            final CompetitionService competitionService = new CompetitionService(webClientService);

            return new FootballDataClient(areaService, teamService, matchService, personService, competitionService);
        }
    }
}
