package src.packages;

import java.util.concurrent.Semaphore;

public class Gate {
    private final Semaphore sem;
    private int id;

    public Gate(int id, Semaphore sem) {
        this.id = id;
        this.sem = sem;
    }

    public int enter(Car c) throws InterruptedException {
        int waitingTime = 0;

        System.out.println(c.toString() + " arrived at time " + c.arrivalTime + ".");

        while (!sem.tryAcquire()) {
            if (waitingTime == 0) {
                System.out.println(c.toString() + " waiting for a spot.");
            }
            Thread.sleep(src.Main.TIME_UNIT); // Wait for a spot
            waitingTime++;
        }

        // Print parking status when the car parks
        System.out.println(c.toString() + " parked"
                + (waitingTime > 0 ? " after waiting for " + waitingTime + " units of time" : ""));

        return waitingTime;
    }

    public void leave(Car c) {
        sem.release();

        System.out.println(c.toString() + " left after " + c.parkingDuration + " units of time.");
        updateParkingStatus();
    }

    private void updateParkingStatus() {
        int occupiedSpots = src.Main.PARK_SPOTS_COUNT - sem.availablePermits();
        System.out.println("(Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public String toString() {
        return "" + this.id;
    }
}
