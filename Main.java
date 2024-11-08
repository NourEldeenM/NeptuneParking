import java.io.*;
import java.util.*;
import packages.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("null");
        List<Car> cars = List.of();
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

                Car car = new Car(carID, arrivalTime, parkingTime,gateNumber);
                cars.add(car);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        cars.sort(Comparator.comparingInt(car -> car.getArrivalTime())); // sorting if not sorted

    }
}
