import java.util.*;
import java.util.concurrent.*;

class ParkingLot {
    private Semaphore spaces;

    public ParkingLot() {

        spaces = new Semaphore(30);
    }

    public void park(int requiredSpaces) {
        try {

            spaces.acquire(requiredSpaces);
            Thread.sleep(requiredSpaces == 1 ? 50 : 70);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
    }

    public void leave(int requiredSpaces) {
        try {

            Thread.sleep(requiredSpaces == 1 ? 50 : 70);
            spaces.release(requiredSpaces);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
    }
}

class Car implements Runnable {
    private ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.park(1);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }

        parkingLot.leave(1);
    }
}

class Bus implements Runnable {
    private ParkingLot parkingLot;

    public Bus(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.park(3);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }

        parkingLot.leave(3);
    }
}

public class plss {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        List<Runnable> vehicles = new ArrayList<>();


        for (int i = 0; i < 30; i++) {
            vehicles.add(new Car(parkingLot));
        }
        for (int i = 0; i < 12; i++) {
            vehicles.add(new Bus(parkingLot));
        }


        Collections.shuffle(vehicles);


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (Runnable vehicle : vehicles) {
            executorService.submit(vehicle);
        }


        executorService.shutdown();

    }
}
