package src.packages;

public class Gate {
    private final CustomSemaphore gateSemaphore;
    private int id;
    
    private synchronized void log(String message) {
        System.out.println(message);
    }
    public Gate(int id, CustomSemaphore sem) {
        this.id = id;
        this.gateSemaphore = sem;
    }

    public int enter(Car c) throws InterruptedException {
        int waitingTime = 0;

       log(c.toString() + " arrived at time " + c.arrivalTime + ".");

        while (!gateSemaphore.tryAcquire()) {
            if (waitingTime == 0) {
                log(c.toString() + " waiting for a spot.");
            }
            Thread.sleep(src.Main.TIME_UNIT); // Wait for a spot
            waitingTime++;
        }

        // Print parking status when the car parks
        log(c.toString() + " parked"
                + (waitingTime > 0 ? " after waiting for " + waitingTime + " units of time" : ""));
        return waitingTime;
    }

    public synchronized void leave(Car c) {
        log(c.toString() + " left after " + c.parkingDuration + " units of time.");
        gateSemaphore.release();
        updateParkingStatus();
    }


    private synchronized void updateParkingStatus() {
        int occupiedSpots = src.Main.PARK_SPOTS_COUNT - gateSemaphore.availablePermits();
        log("(Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public String toString() {
        return "" + this.id;
    }
}
