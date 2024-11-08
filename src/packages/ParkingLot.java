package src.packages;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private static final int TOTAL_SPOTS = 4;

    private final Semaphore parkingSpots;

    private int occupiedSpots = 0;

    /**
     *
     */
    public ParkingLot() {
        this.parkingSpots = new Semaphore(TOTAL_SPOTS);
    }

    // Method to acquire a parking spot

    //If there is 1+ permit available (semaphore’s count is greater than zero), semaphore-- and allows the thread to proceed.
    //If there are no permits available (the count == zero), the thread blocks and waits until another thread releases a permit (by calling .release())

    /**
     * @param carName
     * @throws InterruptedException
     */
    public void acquireSpot(String carName) throws InterruptedException {
        parkingSpots.acquire();
        /*
         The synchronized block in Java is used to ensure mutual exclusion when multiple threads are accessing or modifying a shared resource. When you use synchronized (this) in a method, it locks the object (this refers to the current instance of the class) to prevent other threads from entering any other synchronized blocks on the same object until the lock is released. Here’s a breakdown of what it accomplishes in your acquireSpot method:

        Purpose of synchronized in acquireSpot
        Thread Safety: Since multiple cars (represented by threads) may attempt to park simultaneously, they all call acquireSpot at potentially the same time. The synchronized block ensures that only one thread at a time can execute the code within it, so the occupiedSpots counter is correctly incremented without interference.

        Atomicity: By locking the synchronized block, the increment of occupiedSpots and the System.out.println statement are treated as an atomic operation for each thread. This prevents race conditions where multiple threads might read or write occupiedSpots at the same time, leading to incorrect counts.

        Controlled Output: It ensures that the print statement accurately reflects the state of the occupiedSpots variable without any interleaving of other threads' outputs, making the log consistent and reliable.

        Example Scenario:
                Without synchronized, we might have two threads both reading occupiedSpots as 2, both incrementing it to 3, and both printing the same value, leading to an incorrect count. With synchronized, each thread waits its turn to complete the operation inside the block, ensuring a consistent, accurate count.
         */
        synchronized (this) {
            occupiedSpots++;
            System.out.println(carName + " parked. (Parking Status: " + occupiedSpots + " spots occupied, " + (TOTAL_SPOTS - occupiedSpots) + " spots empty)");
        }
    }

    // Method to release a parking spot

    /**
     * @param carName
     */
    public void releaseSpot(String carName) {
        synchronized (this) {
            occupiedSpots--;
            System.out.println(carName + " left. (Parking Status: " + occupiedSpots + " spots occupied, " + (TOTAL_SPOTS - occupiedSpots) + " spots empty)");
        }

        parkingSpots.release();
    }

    /**
     * @return
     */
    public synchronized String getStatus() {
        int availableSpots = TOTAL_SPOTS - occupiedSpots;
        return "Occupied spots: " + occupiedSpots + ", Available spots: " + availableSpots;
    }


    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        Thread car1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parkingLot.acquireSpot("Car 1");
                    Thread.sleep(1000);
                    parkingLot.releaseSpot("Car 1");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread car2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parkingLot.acquireSpot("Car 2");
                    Thread.sleep(2000);
                    parkingLot.releaseSpot("Car 2");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread car3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parkingLot.acquireSpot("Car 3");
                    Thread.sleep(3000);
                    parkingLot.releaseSpot("Car 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread car4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parkingLot.acquireSpot("Car 4");
                    Thread.sleep(4000);
                    parkingLot.releaseSpot("Car 4");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread car5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parkingLot.acquireSpot("Car 5"); // should be no empty spot before at least 1 car leaves (check this later)
                    Thread.sleep(5000);
                    parkingLot.releaseSpot("Car 5");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        car1.start();
        car2.start();
        car3.start();

        //System.out.println(parkingLot.getStatus() + "\n");

        car4.start();
        car5.start();
    }
}
