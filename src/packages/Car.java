package src.packages;

public class Car implements Runnable {
    public int id;
    public int arrivalTime;
    public int parkingDuration;
    private Gate gate;

    public Car(int carID, int arrivalTime, int parkingDuration, Gate gate) {
        this.id = carID;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.gate = gate;
    }

    public synchronized void park() {
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
            synchronized (this) {
                Thread.sleep(arrivalTime * src.Main.TIME_UNIT);
            }
            gate.enter(this);
            park();
            gate.leave(this);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "Car " + this.id + " from gate " + this.gate.toString();
    }
}