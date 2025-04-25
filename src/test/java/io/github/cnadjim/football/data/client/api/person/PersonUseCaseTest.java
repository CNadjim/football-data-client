package io.github.cnadjim.football.data.client.api.person;

import io.github.cnadjim.football.data.client.api.UseCaseTest;
import io.github.cnadjim.football.data.client.domain.exception.WebClientException;
import io.github.cnadjim.football.data.client.domain.model.Person;
import io.github.cnadjim.football.data.client.domain.response.ClientResponse;
import io.github.cnadjim.football.data.client.domain.response.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static io.github.cnadjim.football.data.client.utils.FileUtils.readFile;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PersonUseCaseTest extends UseCaseTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = readFile("sample/person.json", Person.class);
    }

    @Test
    public void findByPersonId_should_work() {
        // given
        doReturn(ClientResponse.success(person))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Person.class));

        // when
        final Optional<Person> optionalPerson = footballDataHttpClient.person().findByPersonId(8762);

        // then
        Assertions.assertNotNull(optionalPerson);
        Assertions.assertTrue(optionalPerson.isPresent());
        Assertions.assertEquals("Romain Faivre", optionalPerson.get().name());
    }

    @Test
    public void findByPersonId_should_not_throw_but_return_empty_optional() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Error", 404)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Person.class));

        // when
        final Optional<Person> optionalPerson = footballDataHttpClient.person().findByPersonId(8762);

        // then
        Assertions.assertNotNull(optionalPerson);
        Assertions.assertTrue(optionalPerson.isEmpty());
    }

    @Test
    public void findByPersonId_should_throw_web_client_exception() {
        // given
        doReturn(ClientResponse.error(new ErrorResponse("Bad request", 400)))
                .when(httpConnector)
                .send(Mockito.any(), Mockito.any(), Mockito.eq(Person.class));

        // when
        final WebClientException webClientException = Assertions.assertThrows(WebClientException.class, () -> footballDataHttpClient.person().findByPersonId(8762));

        // then
        Assertions.assertEquals("Bad request", webClientException.getMessage());
    }
}
