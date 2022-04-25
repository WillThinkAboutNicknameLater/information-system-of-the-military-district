package ru.nsu.fit.militarysystem.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiValidationError implements ApiSubError {
    private final String object;

    private final String field;

    private final String message;
}
