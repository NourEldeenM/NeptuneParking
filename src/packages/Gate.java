package src.packages;

import java.util.concurrent.Semaphore;

public class Gate {

    private static final int TOTAL_SPOTS = 3;

    private static Semaphore gates;

    public Gate() {
        Gate.gates = new Semaphore(TOTAL_SPOTS);
    }

    public static void arrive(Car c) throws InterruptedException {
        int waitingTime = 0;
        System.out.println("Car " + c.getCarID() + " from Gate " + c.getGateNumber() + " arrived at time "
                + c.getArrivalTime());

        while (!ParkingLot.availableParkSlots()) {
            if (waitingTime == 0) {
                System.out.println("Car " + c.getCarID() + " waiting for a spot.");
            }
            Thread.sleep(1000); // Wait for a spot
            waitingTime++;
        }

        // Print parking status when the car parks
        if (ParkingLot.availableParkSlots()) {
            ParkingLot.acquireSpot();
            System.out.println("Car " + c.getCarID() + " from Gate " + c.getGateNumber() + " parked"
                    + (waitingTime > 0 ? " after waiting for " + waitingTime + " units of time" : "") + ParkingLot
                            .getStatus());
        }
    }

    public static void leave(Car c) {
        ParkingLot.releaseSpot();
        System.out.println("Car " + c.getCarID() + " left after " + c.getParkingDuration() + " units of time.");
    }
}