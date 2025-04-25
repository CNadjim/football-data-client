package io.github.cnadjim.football.data.client.api.person;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PersonUseCaseTest extends UseCaseTest {

    @Test
    public void findByPersonId_should_work() {
        final Optional<Person> optionalPerson = footballDataHttpClient.person().findByPersonId(8762);
        Assertions.assertNotNull(optionalPerson);
        Assertions.assertTrue(optionalPerson.isPresent());
        Assertions.assertTrue(optionalPerson.get().name().equals("Romain Faivre"));
    }
}
