package io.github.yasiekz.event.sourcing;

import java.util.UUID;

public interface AggregateSaver<T extends EventSourcedAggregate> {

    T load(UUID aggregateId);
}
