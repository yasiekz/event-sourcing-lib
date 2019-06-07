package io.github.yasiekz.event.sourcing;

import java.util.UUID;

public interface AggregateLoader<T extends EventSourcedAggregate> {

    T load(UUID aggregateId);
}
