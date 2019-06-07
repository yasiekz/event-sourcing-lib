package io.github.yasiekz.event.sourcing.stub;

import java.util.UUID;

public class ExampleCreated extends ExampleEvent {

    private String name;

    public ExampleCreated(final UUID aggregateId, final String name) {
        super(aggregateId);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    void accept(final ExampleEventSourcedAggregate aggregate) {
        aggregate.applyEvent(this);
    }
}
