package io.github.yasiekz.event.sourcing.stub;

import io.github.yasiekz.event.sourcing.EventSourcedAggregate;
import java.util.UUID;

public class ExampleEventSourcedAggregate extends EventSourcedAggregate<ExampleEvent> {

    public enum Status {ACTIVE, DELETED}

    private Status status;
    private String name;

    private ExampleEventSourcedAggregate() {

    }

    public static ExampleEventSourcedAggregate create(UUID id, String name) {
        ExampleCreated event = new ExampleCreated(id, name);
        ExampleEventSourcedAggregate aggregate = new ExampleEventSourcedAggregate();
        aggregate.applyEvent(event);
        aggregate.recordEvent(event);

        return aggregate;
    }

    public void changeName(String newName) {
        ExampleNameChanged event = new ExampleNameChanged(id, newName);
        applyEvent(event);
        recordEvent(event);
    }

    public void remove() {
        ExampleDeleted event = new ExampleDeleted(id);
        applyEvent(event);
        recordEvent(event);
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    protected void doApplyPastEvent(final ExampleEvent event) {
        event.accept(this);
    }

    void applyEvent(ExampleCreated event) {
        id = event.getAggregateId();
        name = event.getName();
        status = Status.ACTIVE;
    }

    void applyEvent(ExampleNameChanged event) {
        name = event.getNewName();
    }

    void applyEvent(ExampleDeleted event) {
        status = Status.DELETED;
    }
}
