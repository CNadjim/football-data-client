package io.github.cnadjim.football.data.client.domain.exception;

public class ConversionException extends RuntimeException {

    public ConversionException(Class<?> targetClass, Throwable cause) {
        super(String.format("Error occurred while converting into %s", targetClass.getSimpleName()), cause);
    }
}
