package src.packages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class InputParser {

    // Private constructor to prevent creating objects from the class
    private InputParser() {
    }

    /**
     * Parses input from given file name and returns List of Car objects with
     * required details
     * 
     * @param inputFileName
     * @return cars List of cars with their details
     */
    public static List<Car> parseInputFile(String inputFileName) {
        List<Car> cars = new LinkedList<Car>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");
                int gateNumber = Integer.parseInt(parts[0].split(" ")[1]);
                int carID = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingTime = Integer.parseInt(parts[3].split(" ")[1]);

                Car car = new Car(carID, arrivalTime, parkingTime, gateNumber);
                cars.add(car);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
