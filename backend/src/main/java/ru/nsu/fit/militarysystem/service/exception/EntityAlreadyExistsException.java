package ru.nsu.fit.militarysystem.service.exception;

import org.springframework.util.StringUtils;

import java.util.Map;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(Class<?> classObject, Map<String, String> parameters) {
        super(generateMessage(classObject.getSimpleName(), parameters));
    }

    private static String generateMessage(String entity, Map<String, String> parameters) {
        return "Сущность " + StringUtils.capitalize(entity) + " уже существует с данными параметрами " + parameters + ".";
    }
}
