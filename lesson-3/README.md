Lesson-3
---
As mentioned in Lesson-2, Axon framework supports two mechanisms to maintain the state of aggregates.
- Standard Repository to store the state of Aggregate directly
- Event sourcing Repository to store the events ever happened and get the state by replaying all the events

In this lesson, we implement the event sourcing repository, which is the default option when using axon-spring-boot-autoconfigure
module.
All the events will be stored in the MySql through JPA.