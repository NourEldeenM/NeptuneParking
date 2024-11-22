package src.packages;

import src.Main;

import java.util.concurrent.Semaphore;

public class ParkingManager {
    private final Semaphore parkSpots;

    public ParkingManager(int totalSpots) {
        this.parkSpots = new Semaphore(totalSpots);
    }

    public boolean tryPark(Car car) throws InterruptedException {
        while (!parkSpots.tryAcquire()) {
            Thread.sleep(Main.TIME_UNIT); // Wait for a spot
            car.incrementWaitingTime();
        }
        return true;
    }

    public void leaveSpot(Car car) {
        parkSpots.release();
        updateParkingStatus();
    }

    private void updateParkingStatus() {
        int occupiedSpots = Main.PARK_SPOTS_COUNT - parkSpots.availablePermits();
        System.out.println("(Parking Status: " + occupiedSpots + " spots occupied)");
    }
}
