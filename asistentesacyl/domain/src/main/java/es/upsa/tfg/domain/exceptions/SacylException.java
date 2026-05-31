package es.upsa.tfg.domain.exceptions;

public class SacylException extends RuntimeException {
    public SacylException(String message) {
        super(message);
    }

    public SacylException() {
        super();
    }

    public SacylException(String message, Throwable cause) {
        super(message, cause);
    }

    public SacylException(Throwable cause) {
        super(cause);
    }

    protected SacylException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
