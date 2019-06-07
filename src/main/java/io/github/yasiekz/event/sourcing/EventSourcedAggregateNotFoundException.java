package io.github.yasiekz.event.sourcing;

@SuppressWarnings("WeakerAccess")
public class EventSourcedAggregateNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 3141747971445927724L;

    EventSourcedAggregateNotFoundException(final String message) {
        super(message);
    }

    EventSourcedAggregateNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
