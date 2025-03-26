package elevator_synchronization_simulation.src;

import java.util.Random;

class Elevator {
    private int weightLimit;
    private int currentWeight;
    private Object lock = new Object();

    public Elevator(int weightLimit) {
        this.weightLimit = weightLimit;
        this.currentWeight = 0;
    }


    public void enter(int weight) {
        synchronized (lock) {
            while (currentWeight + weight > weightLimit) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            currentWeight += weight;
            lock.notifyAll();
        }
    }


    public void move() {
        synchronized (lock) {
            try {
                Thread.sleep(850);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void leave(int weight) {
        synchronized (lock) {
            currentWeight -= weight;
            lock.notifyAll();
        }
    }
}



class Person implements Runnable {
    private int weight;
    private Elevator elevator;

    public Person(Elevator elevator) {
        this.elevator = elevator;
        this.weight = 60 + new Random().nextInt(41);
    }

    @Override
    public void run() {
        elevator.enter(weight);
        elevator.move();
        elevator.leave(weight);
    }
}


public class ess {
    public static void main(String[] args) {
        int people = 20;
        Elevator elevator = new Elevator(1000);
        for (int i = 0; i < people; i++) {
            Person person = new Person(elevator);
            Thread thread = new Thread(person);
            thread.start();
        }
    }
}


