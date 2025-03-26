# Parking Lot Synchronization Simulation

The task involves simulating a parking lot with a limited number of parking spaces. Multiple vehicles, such as cars and buses, try to park concurrently while following specific space requirements. The simulation uses Java’s `Semaphore` for synchronization to ensure that the parking lot does not get overfilled.

The `ParkingLot` class contains a `Semaphore` to manage the parking spaces, which are initially set to 30. The `park()` method ensures that vehicles acquire the required spaces, while the `leave()` method releases the spaces when the vehicles are done. The vehicles, represented by the `Car` and `Bus` classes, implement the `Runnable` interface, allowing them to be executed concurrently. Cars occupy one space, while buses occupy three spaces. The main class creates a mix of cars and buses, shuffles them to simulate random arrival, and then assigns them to an `ExecutorService` with a fixed thread pool of 10 threads.

The synchronization of the parking lot is handled by the `Semaphore`, ensuring that no vehicle exceeds the available spaces. The vehicles park by acquiring the required spaces and release them when they leave, making the simulation a demonstration of resource management and synchronization in a multithreaded environment.
