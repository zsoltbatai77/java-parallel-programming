# Concurrent Calculation with Cyclic Barrier

The task involves using concurrent programming to simulate a scenario where multiple threads perform calculations on random numbers. The program uses Java’s `CyclicBarrier` to synchronize threads and ensure they all reach a certain point before continuing.

The `Computer` class implements the `Callable` interface and performs a simple calculation on a random parameter. If the parameter is even, it increments a shared `AtomicInteger` result; otherwise, it decrements the result. After performing the operation, each thread waits at the `CyclicBarrier` until all threads have completed their tasks.

The main class, `FEPC5M_2`, sets up an `ExecutorService` with a thread pool and creates 111 `Computer` tasks. Each task is initialized with a random number between 1 and 100 and then executed concurrently. Once all tasks are completed and the threads reach the barrier, the final value of the `AtomicInteger` result is printed.

The program demonstrates how to use a `CyclicBarrier` to coordinate the completion of concurrent tasks and aggregate the results in a thread-safe manner using `AtomicInteger`.
