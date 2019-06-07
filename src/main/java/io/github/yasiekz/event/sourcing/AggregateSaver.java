package io.github.yasiekz.event.sourcing;

public interface AggregateSaver<T extends EventSourcedAggregate> {

    void save(T aggregate);
}
