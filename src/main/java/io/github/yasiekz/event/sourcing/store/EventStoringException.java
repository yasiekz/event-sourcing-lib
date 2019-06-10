package io.github.yasiekz.event.sourcing.store;

public class EventStoringException extends RuntimeException {

    private static final long serialVersionUID = -6459883822460722790L;

    public EventStoringException(final Throwable cause) {
        super(cause);
    }
}
