package src.packages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import src.Main;

public class DriverProgram {

    private static final int GATES_COUNT = Main.GATES_COUNT;
    private static final int PARK_SPOTS_COUNT = Main.PARK_SPOTS_COUNT;
    private static final int TIME_UNIT = Main.TIME_UNIT;


    private DriverProgram() {
    }
    private static List<Gate> gates = new ArrayList<>();

    public static void start(String inputFileName) {

        Semaphore parkSpots = new Semaphore(PARK_SPOTS_COUNT);
        List<Gate> gates = new ArrayList<>();
        for (int i = 0; i < GATES_COUNT; i++) {
            gates.add(new Gate(i + 1, parkSpots));
        }

        List<Car> cars = InputParser.parseInputFile(inputFileName, gates);
        List<Thread> carThreads = new ArrayList<>();
        for (Car c : cars) {
            Thread carThread = new Thread(c, c.toString());
            carThread.start();
            carThreads.add(carThread);
        }

        try {
            for (Thread carThread : carThreads) {
                carThread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        printFinalSummary();

    }

    public static void printAllActiveThreads() {
        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();

        System.out.println("Active threads:");
        for (Thread thread : threads.keySet()) {
            System.out.println("Thread name: " + thread.getName() +
                    ", State: " + thread.getState());
        }
    }

    private static void printFinalSummary() {
        System.out.println("\nDetails:");
        int totalCarsServed = 0;
        for (Gate gate : gates) {
            totalCarsServed+=gate.getCarsServed();
        }
        System.out.println("Total cars served: " + totalCarsServed);
        System.out.println("Total cars currently in parking: " + Main.currentCarsInParking);

        for (Gate gate : gates) {
            System.out.println("Gate " + gate.toString() + " served " + gate.getCarsServed() + " cars.");
        }
    }
}
