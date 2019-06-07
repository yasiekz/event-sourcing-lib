package io.github.yasiekz.event.sourcing.stub;

import io.github.yasiekz.event.sourcing.Event;
import java.util.UUID;

public abstract class ExampleEvent extends Event {

    public ExampleEvent(final UUID aggregateId) {
        super(aggregateId);
    }

    abstract void accept(ExampleEventSourcedAggregate aggregate);
}
