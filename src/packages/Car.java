package src.packages;

public class Car implements Runnable {
    public final int id;
    public final int arrivalTime;
    public final int parkingDuration;
    public int waitingTime;
    private final Gate gate;

    public Car(int carID, int arrivalTime, int parkingDuration, Gate gate) {
        this.id = carID;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.gate = gate;
        this.waitingTime = 0;
    }

    public void park() {
        try {
            Thread.sleep(parkingDuration * src.Main.TIME_UNIT);
        } catch (InterruptedException e) {
            System.err.println("Error during parking: " + e.getMessage());
        }
    }

    public void incrementWaitingTime() {
        waitingTime++;
    }

    @Override
    public void run() {
        try {
            // Wait until arrival time
            Thread.sleep(arrivalTime * src.Main.TIME_UNIT);

            // Use the gate to handle parking
            gate.handleCar(this);
        } catch (InterruptedException e) {
            System.err.println("Error for car " + id + ": " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Car " + id + " (Gate " + gate + ")";
    }
}
