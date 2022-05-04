package ru.nsu.fit.militarysystem.service.exception;

import org.springframework.util.StringUtils;

import java.util.Map;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> classObject, Map<String, String> searchParameters) {
        super(generateMessage(classObject.getSimpleName(), searchParameters));
    }

    private static String generateMessage(String entity, Map<String, String> searchParameters) {
        return StringUtils.capitalize(entity) + " was not found with parameters " + searchParameters;
    }
}
