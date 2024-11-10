package src.packages;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private static final int TOTAL_SPOTS = 4;

    private static Semaphore parkingSpots;

    private static int occupiedSpots = 0;

    private ParkingLot() {
        ParkingLot.parkingSpots = new Semaphore(TOTAL_SPOTS);
    }

    public static void acquireSpot() throws InterruptedException {
        parkingSpots.acquire();
        synchronized (ParkingLot.class) {
            occupiedSpots++;
        }
    }

    // Method to release a parking spot
    public static void releaseSpot() {
        synchronized (ParkingLot.class) {
            occupiedSpots--;
        }
        parkingSpots.release();
    }

    public static synchronized String getStatus() {
        int availableSpots = TOTAL_SPOTS - occupiedSpots;
        return "(Occupied spots: " + occupiedSpots + ", Available spots: " + availableSpots + ")";
    }

    public static boolean availableParkSlots() {
        return occupiedSpots == TOTAL_SPOTS ? false : true;
    }
}
