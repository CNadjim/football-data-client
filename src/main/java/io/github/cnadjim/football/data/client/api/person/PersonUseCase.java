package io.github.cnadjim.football.data.client.api.person;

import io.github.cnadjim.football.data.client.annotation.UseCase;
import io.github.cnadjim.football.data.client.domain.model.Person;

import java.util.Optional;

@UseCase
public interface PersonUseCase {

    Optional<Person> findByPersonId(long personId);
}
