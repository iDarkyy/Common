package me.idarkyy.common.event.exception;

public class EventException extends RuntimeException {
    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(Throwable cause) {
        super(cause);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }
}
