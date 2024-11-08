package packages;

class Car {
    private int carID;
    private int arrivalTime;
    private int parkingDuration;
    private int gateNumber;

    public Car(int carID, int arrivalTime, int parkingDuration) {
    }

    public Car(int carID, int arrivalTime, int parkingTime, int gateNumber) {
        this.carID = carID;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.gateNumber = gateNumber;
    }

    public int getCarID() {
        return carID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getParkingDuration() {
        return parkingDuration;
    }
}
