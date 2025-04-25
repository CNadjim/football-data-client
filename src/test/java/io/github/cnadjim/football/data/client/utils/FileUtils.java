package io.github.cnadjim.football.data.client.utils;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;


public class FileUtils {

    private static final ClassLoader classLoader = FileUtils.class.getClassLoader();

    public static String readFile(String path) {
        try {
            final File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
            return Files.readString(file.toPath());
        } catch (Exception e) {
            return null;
        }
    }
}
