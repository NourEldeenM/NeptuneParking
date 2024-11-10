package src.packages;

import java.util.List;

public class DriverProgram {
    
    private List<Car> cars;

    public DriverProgram(String inputFileName) {
        cars = InputParser.parseInputFile(inputFileName);
    }

    public void startSimulation() {
        // Iterate over the list of cars and create a thread for each one
        for (Car car : cars) {
            CarThread carThread = new CarThread(car);
            carThread.start(); // Start the thread, which will invoke the run() method in CarThread
        }
    }
}
