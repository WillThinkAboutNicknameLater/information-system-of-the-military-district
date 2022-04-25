package ru.nsu.fit.militarysystem.api;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.nsu.fit.militarysystem.api.error.ApiError;
import ru.nsu.fit.militarysystem.api.error.ApiSubError;
import ru.nsu.fit.militarysystem.api.error.ApiValidationError;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        String message = "The '" + ex.getParameterName() + "' parameter is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        String message = ex.getContentType() + " media type is not supported";
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        List<ApiSubError> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiValidationError(error.getObjectName(), error.getField(), error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(new ApiValidationError(error.getObjectName(), null, error.getDefaultMessage()));
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", errors);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        String message = ex.getMethod() + " method is not supported for this request";
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(JDBCConnectionException.class)
    protected ResponseEntity<Object> handleJDBCConnectionException() {
        String message = "Failed to connect to the database";
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExistException(EntityAlreadyExistException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage());
            return buildResponseEntity(apiError);
        }
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "The '" + ex.getName() + "' parameter of value '" + ex.getValue() + "' could not be converted";
        Class<?> requiredType = ex.getRequiredType();
        if (requiredType != null) {
            message += " to type '" + requiredType.getSimpleName() + "'";
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

}
