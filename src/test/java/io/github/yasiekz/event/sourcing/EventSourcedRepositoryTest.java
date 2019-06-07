package io.github.yasiekz.event.sourcing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.github.yasiekz.event.sourcing.store.EventStore;
import io.github.yasiekz.event.sourcing.stub.*;
import io.github.yasiekz.event.sourcing.stub.ExampleEventSourcedAggregate.Status;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventSourcedRepositoryTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "name";
    private static final String NAME2 = "name2";

    @Mock
    private EventStore<ExampleEvent> eventStore;

    private EventSourcedRepository<ExampleEventSourcedAggregate, ExampleEvent> repository;

    @BeforeEach
    void setUp() {
        repository = new EventSourcedRepository<>(eventStore) {
            @Override
            protected ExampleEventSourcedAggregate createInstance() {
                return new ExampleEventSourcedAggregate();
            }
        };
    }

    @Test
    @DisplayName("Should load aggregate from events")
    void testLoad() {

        // given
        ExampleCreated e1 = new ExampleCreated(ID, NAME);
        ExampleNameChanged e2 = new ExampleNameChanged(ID, NAME2);
        ExampleDeleted e3 = new ExampleDeleted(ID);
        when(eventStore.load(ID)).thenReturn(List.of(e1, e2, e3));

        // when
        final ExampleEventSourcedAggregate aggregate = repository.load(ID);

        // then
        assertEquals(NAME2, aggregate.getName());
        assertEquals(Status.DELETED, aggregate.getStatus());
    }

    @Test
    @DisplayName("Should save events from given aggregate")
    void testSave() {

        // given
        ExampleCreated e1 = new ExampleCreated(ID, NAME);
        ExampleNameChanged e2 = new ExampleNameChanged(ID, NAME2);
        ExampleDeleted e3 = new ExampleDeleted(ID);
        ExampleEventSourcedAggregate aggregate = mock(ExampleEventSourcedAggregate.class);
        when(aggregate.getRecordedEvents()).thenReturn(List.of(e1, e2, e3));

        // when
        repository.save(aggregate);

        // then
        verify(eventStore, times(1)).save(e1);
        verify(eventStore, times(1)).save(e2);
        verify(eventStore, times(1)).save(e3);
        verify(aggregate, times(1)).markEventsAsSaved();
    }
}