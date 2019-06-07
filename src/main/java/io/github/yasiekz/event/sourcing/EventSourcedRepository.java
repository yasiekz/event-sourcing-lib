package io.github.yasiekz.event.sourcing;

import io.github.yasiekz.event.sourcing.store.EventStore;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
public abstract class EventSourcedRepository<T extends EventSourcedAggregate<E>, E extends Event>
    implements AggregateLoader<T>, AggregateSaver<T> {

    private final EventStore<E> eventStore;

    public EventSourcedRepository(final EventStore<E> eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public T load(final UUID aggregateId) {
        try {
            final T instance = createInstance();
            eventStore.load(aggregateId).forEach(instance::applyPastEvent);

            return instance;

        } catch (EventsForAggregateNotFoundException e) {
            throw new EventSourcedAggregateNotFoundException("Could not found aggregate with ID = " + aggregateId, e);
        }
    }

    @Override
    public void save(final T aggregate) {
        aggregate.getRecordedEvents().forEach(eventStore::save);
        aggregate.markEventsAsSaved();
    }

    protected abstract T createInstance();
}
