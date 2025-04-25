package io.github.cnadjim.football.data.client.stub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class InMemoryTokenRegistryTest {

    @Test
    public void constructor_shouldCreateInstance_whenTokensProvided() {
        // Given
        CopyOnWriteArraySet<String> tokens = new CopyOnWriteArraySet<>(List.of("token1", "token2"));

        // When
        InMemoryTokenRegistry registry = new InMemoryTokenRegistry(tokens);

        // Then
        Assertions.assertNotNull(registry);
        Assertions.assertEquals(tokens, registry.tokens());
        Assertions.assertEquals(2, registry.tokens().size());
    }


    @Test
    public void collectionConstructor_shouldCreateRegistryWithTokens() {
        // Given
        Collection<String> tokens = Arrays.asList("token1", "token2", "token3");

        // When
        InMemoryTokenRegistry registry = new InMemoryTokenRegistry(tokens);

        // Then
        Assertions.assertNotNull(registry);
        Assertions.assertEquals(3, registry.tokens().size());
        Assertions.assertTrue(registry.tokens().contains("token1"));
        Assertions.assertTrue(registry.tokens().contains("token2"));
        Assertions.assertTrue(registry.tokens().contains("token3"));
    }

    @Test
    public void of_shouldCreateRegistryWithSingleToken() {
        // Given
        String token = "singleToken";

        // When
        InMemoryTokenRegistry registry = InMemoryTokenRegistry.of(token);

        // Then
        Assertions.assertNotNull(registry);
        Assertions.assertEquals(1, registry.tokens().size());
        Assertions.assertTrue(registry.tokens().contains(token));
    }

    @Test
    public void findAll_shouldReturnAllTokens() {
        // Given
        Collection<String> tokens = Arrays.asList("token1", "token2");
        InMemoryTokenRegistry registry = new InMemoryTokenRegistry(tokens);

        // When
        Collection<String> result = registry.findAll();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains("token1"));
        Assertions.assertTrue(result.contains("token2"));
    }

    @Test
    public void iterator_shouldReturnIteratorOfTokens() {
        // Given
        Collection<String> tokens = Arrays.asList("token1", "token2");
        InMemoryTokenRegistry registry = new InMemoryTokenRegistry(tokens);

        // When
        var iterator = registry.iterator();

        // Then
        Assertions.assertNotNull(iterator);
        Assertions.assertTrue(iterator.hasNext());
        String firstToken = iterator.next();
        Assertions.assertTrue(tokens.contains(firstToken));
        Assertions.assertTrue(iterator.hasNext());
        String secondToken = iterator.next();
        Assertions.assertTrue(tokens.contains(secondToken));
        Assertions.assertFalse(iterator.hasNext());
    }
}
