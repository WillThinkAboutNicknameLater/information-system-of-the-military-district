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
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          @NonNull HttpHeaders headers,
                                                                          @NonNull HttpStatus status,
                                                                          @NonNull WebRequest request) {
        String title = "?????????????????? ????????????";
        String message = "???????????????? ???????????????? ????????????????'" + ex.getParameterName() + "'.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, title, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     @NonNull HttpHeaders headers,
                                                                     @NonNull HttpStatus status,
                                                                     @NonNull WebRequest request) {
        String title = "?????????????????? ????????????";
        String message = "???????????? MediaType '" + ex.getContentType() + "' ???? ????????????????????????????.";
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, title, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        List<ApiSubError> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiValidationError(error.getObjectName(), error.getField(), error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(new ApiValidationError(error.getObjectName(), null, error.getDefaultMessage()));
        }
        String title = "?????????????????? ????????????";
        String message = "???????????? ?????????????????? ??????????????????????????????.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, title, message, errors);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   @NonNull HttpHeaders headers,
                                                                   @NonNull HttpStatus status,
                                                                   @NonNull WebRequest request) {
        String title = "?????????????????? ????????????";
        String message = "???????????? ???????????? ???? ?????????? ??????????????????????.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, title, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         @NonNull HttpHeaders headers,
                                                                         @NonNull HttpStatus status,
                                                                         @NonNull WebRequest request) {
        String title = "?????????????????? ????????????";
        String message = "???????????? RequestMethod '" + ex.getMethod() + "' ???? ????????????????????????????.";
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, title, message);
        return buildResponseEntity(apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             @NonNull HttpHeaders headers,
                                                             @NonNull HttpStatus status,
                                                             @NonNull WebRequest request) {
        String title = "?????????????????? ????????????";
        String message = ex.getLocalizedMessage();
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, title, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(JDBCConnectionException.class)
    protected ResponseEntity<Object> handleJDBCConnectionException() {
        String title = "???????????? ?? ???????? ????????????";
        String message = "???? ?????????????? ???????????????????????? ?? ???????? ????????????.";
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, title, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        String title = "???????????? ?? ???????? ????????????";
        String message = ex.getLocalizedMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, title, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        String title = "???????????? ?? ???????? ????????????";
        String message = ex.getLocalizedMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, title, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String title = "???????????? ?? ???????? ????????????";
        if (ex.getCause() instanceof ConstraintViolationException) {
            String message = "?????? ???????????? ???????????????? ???????????????? ??????????????????????.";
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, title, message);
            return buildResponseEntity(apiError);
        }
        String message = ex.getLocalizedMessage();
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, title, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String title = "?????????????????? ????????????";
        String message = "???????????????? '" + ex.getName() + "' ???? ?????????????????? '" + ex.getValue() + "' ???? ?????????? ???????? ??????????????????????????";
        Class<?> requiredType = ex.getRequiredType();
        if (requiredType != null) {
            message += " ?? ?????? '" + requiredType.getSimpleName() + "'";
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, title, message + ".");
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

}
