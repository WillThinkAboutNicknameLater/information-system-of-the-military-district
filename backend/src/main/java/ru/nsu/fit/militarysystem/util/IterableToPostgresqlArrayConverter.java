package ru.nsu.fit.militarysystem.util;

import java.util.Objects;
import java.util.StringJoiner;

public class IterableToPostgresqlArrayConverter {
    private IterableToPostgresqlArrayConverter() {
    }

    public static String convert(Iterable<?> iterable) {
        StringJoiner stringJoiner = new StringJoiner(",");
        if (Objects.nonNull(iterable)) {
            iterable.forEach(element -> stringJoiner.add(element.toString()));
        }
        return "{" + stringJoiner + "}";
    }
}
