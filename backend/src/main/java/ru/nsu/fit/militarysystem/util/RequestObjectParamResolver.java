package ru.nsu.fit.militarysystem.util;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RequestObjectParamResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper mapper;

    public RequestObjectParamResolver(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supportsParameter(final MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(RequestObjectParam.class) != null;
    }

    @Override
    public Object resolveArgument(final MethodParameter methodParameter,
                                  final ModelAndViewContainer modelAndViewContainer,
                                  @NonNull final NativeWebRequest nativeWebRequest,
                                  final WebDataBinderFactory webDataBinderFactory) throws Exception {
        List<Field> parameterFields = getAllClassFields(methodParameter.getParameterType());
        Map<String, Class<?>> fieldMap = new HashMap<>();
        for (Field parameterField : parameterFields) {
            JsonAlias jsonAlias = parameterField.getAnnotation(JsonAlias.class);
            Class<?> parameterFieldType = parameterField.getType();
            if (jsonAlias != null) {
                for (String aliasValue : jsonAlias.value()) {
                    fieldMap.put(aliasValue, parameterFieldType);
                }
            } else {
                fieldMap.put(parameterField.getName(), parameterFieldType);
            }
            JsonProperty jsonProperty = parameterField.getAnnotation(JsonProperty.class);
            if (jsonProperty != null) {
                fieldMap.put(jsonProperty.value(), parameterFieldType);
            }
        }

        Map<String, Object> parameterMap = new HashMap<>();
        nativeWebRequest.getParameterMap().forEach((key, value) -> {
            if (fieldMap.containsKey(key)) {
                Class<?> type = fieldMap.get(key);
                if (Iterable.class.isAssignableFrom(type) || type.isArray()) {
                    parameterMap.put(key, value);
                } else {
                    parameterMap.put(key, value[0]);
                }
            }
        });

        String json = mapper.writeValueAsString(parameterMap);
        return mapper.readValue(json, methodParameter.getParameterType());
    }

    private List<Field> getAllClassFields(Class<?> clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> result = new ArrayList<>(getAllClassFields(clazz.getSuperclass()));
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
        result.addAll(fields);
        return result;
    }
}
