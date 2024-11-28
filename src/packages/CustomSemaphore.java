package src.packages;

public class CustomSemaphore {
    private int permits;

    public CustomSemaphore(int numberOfPermits) {
        if (numberOfPermits < 0) {
            throw new IllegalArgumentException("Permit count cannot be negative");
        }
        this.permits = numberOfPermits;
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
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notify();
    }

    public synchronized int availablePermits() {
        return permits;
    }
}