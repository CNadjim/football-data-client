package io.github.cnadjim.football.data.client.application;

import io.github.cnadjim.football.data.client.annotation.DomainService;
import io.github.cnadjim.football.data.client.api.person.PersonUseCase;
import io.github.cnadjim.football.data.client.domain.model.Person;

import java.util.Optional;

@DomainService
public class PersonService implements PersonUseCase {

    private final WebClientService webClientService;

    public PersonService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public Optional<Person> findByPersonId(long personId) {
        return webClientService.GET(String.format("/persons/%s", personId), Person.class);
    }
}
