[![Build Status](https://travis-ci.org/yasiekz/event-sourcing-lib.svg?branch=master)](https://travis-ci.org/yasiekz/event-sourcing-lib.svg?branch=master)

# Event sourcing lib

### Key features
- Define some abstraction on very top level
- EventSourced repository which works with every event store which fulfils the `EventStore` interface
- Could be extended by other libraries which provide concrete implementations of event store
- Show how to use it with very simple example

### How to use it
Probably you want to use this library with some sping 2.x. In such case you just need to: 

###### Add dependency with Maven:

TBD

###### or with Gradle

TBD

###### Create some Event Store implementation

You just need to create implementation of `EventStore` interface. You can search my others repositories looking for 
it. I want to create Mongo and MS SQL implementations with SpringData.


###### Create configuration class

```java
@Configuration
public class EventSourcingConfiguration {
    
    @Bean
    public EventSourcedRepository<YourEventSourcedAggregate> createRepository(EventStore eventStore) {
        return new EventSourcedRepository<>(eventStore) {
            @Override
            protected YourEventSourcedAggregate createInstance() {
                return new YourEventSourcedAggregate();
            }
        };
    }
}
```


###### Make an event sourced aggregate

There is an full working example in tests.

###### Inject created before repository into your domain logic and use it


### Run tests

```bash
./gradlew check
```