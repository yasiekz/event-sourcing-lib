package io.github.yasiekz.event.sourcing;

@SuppressWarnings("unused")
public class EventsForAggregateNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1471889107908146128L;

    public EventsForAggregateNotFoundException(final String message) {
        super(message);
    }
}
