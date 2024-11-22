package src.packages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.Main;

public class DriverProgram {
    private static final int GATES_COUNT = Main.GATES_COUNT;
    private static final int PARK_SPOTS_COUNT = Main.PARK_SPOTS_COUNT;

    private DriverProgram() {
    }

    public static void start(String inputFileName) {
        // Initialize the ParkingManager
        ParkingManager parkingManager = new ParkingManager(PARK_SPOTS_COUNT);

        // Initialize gates
        List<Gate> gates = new ArrayList<>();
        for (int i = 0; i < GATES_COUNT; i++) {
            gates.add(new Gate(i + 1, parkingManager));
        }

        // Parse cars and assign gates
        List<Car> cars = InputParser.parseInputFile(inputFileName, gates);

        // Start car threads
        for (Car car : cars) {
            new Thread(car, car.toString()).start();
        }
    }

    public static void printAllActiveThreads() {
        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
        System.out.println("Active threads:");
        for (Thread thread : threads.keySet()) {
            System.out.println("Thread name: " + thread.getName() +
                    ", State: " + thread.getState());
        }
    }
}
