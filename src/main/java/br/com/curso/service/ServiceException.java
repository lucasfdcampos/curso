package br.com.curso.service;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "Message: " + getMessage() + " - Cause: " + getCause();
    }
}