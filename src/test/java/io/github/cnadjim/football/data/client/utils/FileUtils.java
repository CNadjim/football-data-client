package io.github.cnadjim.football.data.client.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

import static io.github.cnadjim.football.data.client.stub.JacksonBoyHandler.buildObjectMapper;


public class FileUtils {

    private static final ClassLoader classLoader = FileUtils.class.getClassLoader();
    private static final ObjectMapper objectMapper = buildObjectMapper();

    public static String readFile(String path) {
        try {
            final File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
            return Files.readString(file.toPath());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <T> T readFile(String path, Class<T> clazz) {
        final String fileAsString = readFile(path);
        try {
            return objectMapper.readValue(fileAsString, clazz);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
