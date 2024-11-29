package src.packages;


import static src.Main.TIME_UNIT;

public class Gate {
    private final CustomSemaphore gateSemaphore;
    private int id;
    private int carsServed = 0;

    private synchronized void log(String message) {
        System.out.println(message);
    }
    public Gate(int id, CustomSemaphore sem) {
        this.id = id;
        this.gateSemaphore = sem;
    }

    public void enter(Car c) throws InterruptedException {
        long waitingTime = System.currentTimeMillis();

       log(c.toString() + " arrived at time " + c.arrivalTime + ".");

        gateSemaphore.acquire();

        // Print parking status when the car parks
        carsServed++;

        long currentTime = System.currentTimeMillis();
        long elapsedTimeMillis = currentTime - waitingTime;
        waitingTime = elapsedTimeMillis / TIME_UNIT;

        log(c.toString() + " parked"
                + (waitingTime > 0
                        ? " after waiting for " + waitingTime + " units of time (Parking Status: "
                                + (4 - gateSemaphore.availablePermits())
                                + " spots occupied)"
                        : " (Parking Status: " + (src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits())
                                + " spots occupied)"));
        src.Main.currentCarsInParking = src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits();

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
