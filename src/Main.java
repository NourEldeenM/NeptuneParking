package src;

import src.packages.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;


public class Main {
    public final static int gatesCount=3;
    public static int parkSpotsCount=4;

    public static final  int timeUnit=1000;
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Car> cars = new ArrayList<>();

        Semaphore parkSpots=new Semaphore(parkSpotsCount);
        List<Gate> gates=new ArrayList<>();
        for (int i = 0; i < gatesCount; i++) {
            gates.add(new Gate(i+1,parkSpots));
        }
        /**
         * parsing input into car parking details
         */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");
                int gateNumber = Integer.parseInt(parts[0].split(" ")[1]);
                int carID = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingTime = Integer.parseInt(parts[3].split(" ")[1]);

                Car car = new Car(carID, arrivalTime, parkingTime,gates.get(gateNumber-1));
                cars.add(car);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Car c : cars) {
            Thread carThread = new Thread(c, c.toString());
            carThread.start();
        }
//        System.out.println(Thread.activeCount());


    }

    public static void printAllActiveThreads() {
        // Get all active threads and their stack traces
        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();

        System.out.println("Active threads:");
        for (Thread thread : threads.keySet()) {
            System.out.println("Thread name: " + thread.getName() +
                    ", State: " + thread.getState());
        }
    }
}
