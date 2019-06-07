package io.github.yasiekz.event.sourcing.stub;

import java.util.UUID;

public class ExampleDeleted extends ExampleEvent {

    public ExampleDeleted(final UUID aggregateId) {
        super(aggregateId);
    }

    @Override
    public void accept(final ExampleEventSourcedAggregate aggregate) {
        aggregate.applyEvent(this);
    }
}
