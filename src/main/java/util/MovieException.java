package util;

public class MovieException extends RuntimeException {

    public MovieException() {
    }

    public MovieException(String message) {
        super(message);
    }

    public MovieException(String message, Throwable t) {
        super(message, t);
    }

}
