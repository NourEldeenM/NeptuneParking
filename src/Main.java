package src;

import src.packages.DriverProgram;

public class Main {
    public static void main(String[] args) {
        DriverProgram dp = new DriverProgram("input.txt");
        dp.startSimulation();
    }
}
