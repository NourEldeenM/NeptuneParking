package src.packages;

public class CarThread extends Thread {
    private ParkingLot parkingLot;
    private String carName;
    private long parkingDuration;
    private long arrivalDuration;
    private int gateNumber;

    public CarThread(ParkingLot parkingLot, String carName, long arrivalDuration, long parkingDuration, int gateNumber) {
        this.parkingLot = parkingLot;
        this.carName = carName;
        this.parkingDuration = parkingDuration;
        this.arrivalDuration = arrivalDuration;
        this.gateNumber = gateNumber;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(arrivalDuration);
            System.out.println("Car " + carName + " from Gate " + gateNumber + " arrived at time " + arrivalDuration);

            // Try to acquire a parking spot
            parkingLot.acquireSpot(carName);

            // Simulate the car occupying the spot for a duration
            Thread.sleep(parkingDuration);

            // Release the parking spot
            parkingLot.releaseSpot(carName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
