package src.packages;

import java.util.LinkedList;
import java.util.Queue;

public class CustomSemaphore {
    private int permits;
    private final Queue<Thread> waitingQueue;

    private synchronized void log(String message) {
        System.out.println(message);
    }

    public CustomSemaphore(int numberOfPermits) {
        if (numberOfPermits < 0) {
            throw new IllegalArgumentException("Permit count cannot be negative");
        }
        this.permits = numberOfPermits;
        this.waitingQueue = new LinkedList<>();
    }

    public synchronized boolean tryAcquire() {
        if (permits > 0) {
            permits--;
            return true;
        }
        return false;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits < 1) {
            Thread currentThread = Thread.currentThread();
            waitingQueue.add(currentThread);

            // Print thread properties when added to the queue
//            log("\u001B[31m"+"Thread added to queue: " +
//                    "Name=" + currentThread.getName() + "\u001B[0m");

            try {
                wait();
            } catch (InterruptedException e) {
                waitingQueue.remove(currentThread);
                throw e; // Propagate the exception
            }
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        if (!waitingQueue.isEmpty()) {
            Thread nextThread = waitingQueue.poll();
            notify();
        }
    }

    public synchronized int availablePermits() {
        return permits;
    }
}