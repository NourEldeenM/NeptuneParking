package src.packages;

public class Gate {
    private final CustomSemaphore gateSemaphore;
    private int id;
    private int carsServed = 0;


    public Gate(int id, CustomSemaphore sem) {
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
            Thread.sleep(src.Main.TIME_UNIT); // Wait for a spot
            waitingTime++;
        }

        carsServed++;
        // Print parking status when the car parks
        System.out.println(c.toString() + " parked"
                + (waitingTime > 0 ? " after waiting for " + waitingTime + " units of time" : ""));
        updateParkingStatus();
        src.Main.currentCarsInParking++;
        return waitingTime;
    }

    public void leave(Car c) {
        gateSemaphore.release();

        System.out.println(c.toString() + " left after " + c.parkingDuration + " units of time.");
        updateParkingStatus();
    }

    private synchronized void updateParkingStatus() {
        int occupiedSpots = src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits();
        src.Main.currentCarsInParking = occupiedSpots;
        System.out.println("(Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public int getCarsServed() {
        return carsServed;
    }

    public String toString() {
        return "" + this.id;
    }
}
