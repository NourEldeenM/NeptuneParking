package src.packages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private InputParser() {
    }

    public static List<Car> parseInputFile(String inputFile, List<Gate> gates) {
        List<Car> cars = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");
                int gateNumber = Integer.parseInt(parts[0].split(" ")[1]);
                int carID = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingTime = Integer.parseInt(parts[3].split(" ")[1]);

                Car car = new Car(carID, arrivalTime, parkingTime, gates.get(gateNumber - 1));
                cars.add(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }
}