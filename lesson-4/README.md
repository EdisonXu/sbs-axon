Lesson-4
---
From this lesson, we will try to use Axon to implement a more complex case - the online shop.
We'll climb the mountain step by step.
In this lesson, we simply learn the following things.
- How to use MongoDB as the event store
- How to use MySql as the query database
- In the CQRS, how to separate the command & query

Note:
To avoid losing accuracy caused by using `float/double`, I multiply 100 with the price, and use `long` to store the price.