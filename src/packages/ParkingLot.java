package src.packages;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private static final int TOTAL_SPOTS = 4;

    private final Semaphore parkingSpots;

    private int occupiedSpots = 0;

    public ParkingLot() {
        this.parkingSpots = new Semaphore(TOTAL_SPOTS);
    }

    public void acquireSpot(String carName) throws InterruptedException {
        parkingSpots.acquire();
        synchronized (this) {
            occupiedSpots++;
            System.out.println(carName + " parked. (Parking Status: " + occupiedSpots + " spots occupied, " + (TOTAL_SPOTS - occupiedSpots) + " spots empty)");
        }
    }

    public void releaseSpot(String carName) {
        synchronized (this) {
            occupiedSpots--;
            System.out.println(carName + " left. (Parking Status: " + occupiedSpots + " spots occupied, " + (TOTAL_SPOTS - occupiedSpots) + " spots empty)");
        }

        parkingSpots.release();
    }

    public synchronized String getStatus() {
        int availableSpots = TOTAL_SPOTS - occupiedSpots;
        return "Occupied spots: " + occupiedSpots + ", Available spots: " + availableSpots;
    }

}
