package io.github.yasiekz.event.sourcing.store;

import io.github.yasiekz.event.sourcing.Event;
import io.github.yasiekz.event.sourcing.EventsForAggregateNotFoundException;
import java.util.List;
import java.util.UUID;

public interface EventStore<T extends Event> {

    void save(T event);

    List<T> load(UUID aggregateId) throws EventsForAggregateNotFoundException;
}
