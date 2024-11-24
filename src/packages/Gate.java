package src.packages;

import java.util.concurrent.Semaphore;

public class Gate {
    private final Semaphore gateSemaphore;
    private int id;
    private int carsServed = 0;


    public Gate(int id, Semaphore sem) {
        this.id = id;
        this.gateSemaphore = sem;
    }

    public int enter(Car c) throws InterruptedException {
        int waitingTime = 0;

        System.out.println(c.toString() + " arrived at time " + c.arrivalTime + ".");

        while (!gateSemaphore.tryAcquire()) {
            if (waitingTime == 0) {
                System.out.println(c.toString() + " waiting for a spot.");
            }
            Thread.sleep(src.Main.TIME_UNIT);
            waitingTime++;
        }


        System.out.println(c.toString() + " parked"
                + (waitingTime > 0
                        ? " after waiting for " + waitingTime + " units of time (Parking Status: "
                                + (4 - gateSemaphore.availablePermits())
                                + " spots occupied)"
                        : " (Parking Status: " + (src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits())
                                + " spots occupied)"));
        return waitingTime;
    }

    public void leave(Car c) {
        gateSemaphore.release();
        System.out.println(c.toString() + " left after " + c.parkingDuration + " units of time. (Parking Status: "
                + (src.Main.PARK_SPOTS_COUNT
                        - gateSemaphore.availablePermits())
                + " spots occupied)");
    }

    public int getCarsServed() {
        return carsServed;
    }

    public String toString() {
        return "" + this.id;
    }
}
