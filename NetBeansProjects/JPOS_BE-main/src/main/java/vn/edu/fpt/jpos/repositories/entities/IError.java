package vn.edu.fpt.jpos.repositories.entities;

public class IError extends RuntimeException {

    public IError(String message) {
        super(message);
    }

    public IError(String message, Throwable cause) {
        super(message, cause);
    }

}
