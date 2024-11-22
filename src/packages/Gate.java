package src.packages;

public class Gate {
    private final int id;
    private final ParkingManager parkingManager;

    public Gate(int id, ParkingManager parkingManager) {
        this.id = id;
        this.parkingManager = parkingManager;
    }

    public void handleCar(Car car) {
        try {
            System.out.println(car + " arrived at time " + car.arrivalTime + ".");

            // Try parking the car
            parkingManager.tryPark(car);

            // Car is parked
            System.out.println(car + " parked" +
                    (car.waitingTime > 0 ? " after waiting for " + car.waitingTime + " units of time." : ""));

            // Simulate parking duration
            car.park();

            // Leave the parking spot
            parkingManager.leaveSpot(car);
            System.out.println(car + " left after " + car.parkingDuration + " units of time.");
        } catch (InterruptedException e) {
            System.err.println("Error handling car: " + car + ". " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Gate " + this.id;
    }
}
