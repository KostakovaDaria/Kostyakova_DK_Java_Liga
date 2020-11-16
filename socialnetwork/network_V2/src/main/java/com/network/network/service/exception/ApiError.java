package com.network.network.service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private HttpStatus status;
    private String message;

    private String debugMessage;

    private List<FieldValidationError> fieldValidationErrors;

    ApiError()
    {}

    ApiError(HttpStatus status)
    {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex)
    {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.setDebugMessage(ex.getLocalizedMessage());
    }

    ApiError(HttpStatus status, String message, Throwable ex)
    {
        this();
        this.status = status;
        this.message = message;
        this.setDebugMessage(ex.getLocalizedMessage());
    }

    public String getMessage()
    {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<FieldValidationError> getFieldValidationErrors() {
        return fieldValidationErrors;
    }

    public void setFieldValidationErrors(List<FieldValidationError> fieldValidationErrors) {
        this.fieldValidationErrors = fieldValidationErrors;
    }

    private void addSubError(FieldValidationError subError)
    {
        if (fieldValidationErrors == null)
        {
            fieldValidationErrors = new ArrayList<>();
        }
        fieldValidationErrors.add(subError);
    }

    void addValidationErrors(List<FieldError> fieldErrors)
    {
        fieldErrors.forEach(error ->
        {
            FieldValidationError subError = new FieldValidationError();
            subError.setField(error.getField());
            subError.setMessage(error.getDefaultMessage());
            subError.setRejectedValue(error.getRejectedValue());
            subError.setObject(error.getObjectName());
            this.addSubError(subError);
        });
    }
}
