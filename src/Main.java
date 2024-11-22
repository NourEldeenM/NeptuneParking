package src;

import src.packages.*;

public class Main {
    public static final int GATES_COUNT = 3;
    public static final int PARK_SPOTS_COUNT = 4;
    public static final int TIME_UNIT = 1000; // 1 second per unit

    public static void main(String[] args) {
        DriverProgram.start("input.txt");
    }
}
