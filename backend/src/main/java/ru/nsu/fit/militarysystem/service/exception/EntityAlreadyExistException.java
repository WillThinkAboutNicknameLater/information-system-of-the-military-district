package ru.nsu.fit.militarysystem.service.exception;

import org.springframework.util.StringUtils;

import java.util.Map;

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(Class<?> classObject, Map<String, String> searchParameters) {
        super(generateMessage(classObject.getSimpleName(), searchParameters));
    }

    private static String generateMessage(String entity, Map<String, String> searchParameters) {
        return StringUtils.capitalize(entity) + " already exist for parameters " + searchParameters;
    }
}
