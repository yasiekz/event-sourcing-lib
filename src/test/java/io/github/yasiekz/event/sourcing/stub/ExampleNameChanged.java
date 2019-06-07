package io.github.yasiekz.event.sourcing.stub;

import java.util.UUID;

public class ExampleNameChanged extends ExampleEvent {

    private String newName;

    public ExampleNameChanged(final UUID aggregateId, final String newName) {
        super(aggregateId);
        this.newName = newName;
    }

    @Override
    public void accept(final ExampleEventSourcedAggregate aggregate) {
        aggregate.applyEvent(this);
    }

    public String getNewName() {
        return newName;
    }
}
