package src.packages;

public class Car implements Runnable {
    public int id;
    public int arrivalTime;
    public int parkingDuration;
    private Gate gate;
    public int waitingTime;

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
            System.out.println(e.getMessage());
        }
    }

    public void leave() {
        gate.leave(this);
    }

    @Override
    public void run() {
        try {
            // Arrival time delay
            Thread.sleep(arrivalTime * src.Main.TIME_UNIT);
            // Enter park
            this.waitingTime = gate.enter(this);
            // Park the car
            park();
            // Leave the parking
            leave();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "Car " + this.id + " from gate " + this.gate.toString();
    }
}
