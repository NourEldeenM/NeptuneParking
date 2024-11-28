package src.packages;

import java.util.concurrent.Semaphore;

public class Gate {
    private final Semaphore gateSemaphore;
    private int id;
    private int carsServed = 0;


    private synchronized void log(String message) {
        System.out.println(message);
    }
    public Gate(int id, Semaphore sem) {
        this.id = id;
        this.gateSemaphore = sem;
    }

    public synchronized int enter(Car c) throws InterruptedException {
        int waitingTime = 0;

       log(c.toString() + " arrived at time " + c.arrivalTime + ".");

        while (!gateSemaphore.tryAcquire()) {
            if (waitingTime == 0) {
                log(c.toString() + " waiting for a spot.");
            }
            Thread.sleep(src.Main.TIME_UNIT);
            waitingTime++;
        }
        // Print parking status when the car parks
        carsServed++;
        log(c.toString() + " parked"
                + (waitingTime > 0
                        ? " after waiting for " + waitingTime + " units of time (Parking Status: "
                                + (4 - gateSemaphore.availablePermits())
                                + " spots occupied)"
                        : " (Parking Status: " + (src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits())
                                + " spots occupied)"));
        src.Main.currentCarsInParking = src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits();
        return waitingTime;
    }

    public synchronized void leave(Car c) {
        gateSemaphore.release();
        int occupiedSpots = src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits();
        log(c.toString() + " left after " + c.parkingDuration + " units of time."+" (Parking Status: " + occupiedSpots + " spots occupied)");
        src.Main.currentCarsInParking = src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits();
    }

    public int getCarsServed() {
        return carsServed;
    }

    public String toString() {
        return "" + this.id;
    }
}
