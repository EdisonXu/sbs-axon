Lesson-2
---
In this sample, we integrate SpringBoot and Axon framework to do the same thing with Lesson-1.
Meanwhile, we also learned how to use JPA repository to store the state of Aggregates, which is one of the two ways supported
by Axon,
- Standard Repository to store the state of Aggregate directly
- Event sourcing Repository to store the events ever happened and get the state by replaying all the events