package io.github.yasiekz.event.sourcing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
public abstract class EventSourcedAggregate<T extends Event> {

    protected UUID id;
    private List<T> events = new ArrayList<>();

    public void markEventsAsSaved() {
        clearRecordedEvents();
    }

    public List<T> getRecordedEvents() {
        return events;
    }

    public final void applyPastEvent(T event) {
        doApplyPastEvent(event);
    }

    public UUID getId() {
        return id;
    }

    protected abstract void doApplyPastEvent(T event);

    protected void recordEvent(T event) {
        events.add(event);
    }

    private void clearRecordedEvents() {
        events.clear();
    }
}
