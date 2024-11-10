package src.packages;

public class CarThread extends Thread {
    private Car car;

    public CarThread(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(car.getArrivalTime());
            Gate.arrive(car);
            Thread.sleep(car.getParkingDuration());
            Gate.leave(car);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
