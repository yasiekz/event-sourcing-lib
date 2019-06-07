package io.github.yasiekz.event.sourcing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.yasiekz.event.sourcing.stub.ExampleEventSourcedAggregate;
import io.github.yasiekz.event.sourcing.stub.ExampleEventSourcedAggregate.Status;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventSourcedAggregateTest {

    private static final String FIRST_NAME = RandomStringUtils.randomAlphabetic(10);
    private static final String SECOND_NAME = RandomStringUtils.randomAlphabetic(10);
    private static final String THIRD_NAME = RandomStringUtils.randomAlphabetic(10);

    @Test
    @DisplayName("Should create aggregate and changed it with given events")
    void testAggregate() {

        // given
        UUID id = UUID.randomUUID();

        // when
        ExampleEventSourcedAggregate aggregate = ExampleEventSourcedAggregate.create(id, FIRST_NAME);
        aggregate.changeName(SECOND_NAME);
        aggregate.changeName(THIRD_NAME);
        aggregate.remove();

        // then
        assertEquals(THIRD_NAME, aggregate.getName());
        assertEquals(Status.DELETED, aggregate.getStatus());
        assertEquals(4, aggregate.getRecordedEvents().size());
    }
}
