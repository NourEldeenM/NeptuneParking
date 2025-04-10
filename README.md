# Multithreaded Parking System Simulation

## Project Overview
This repository contains a Java-based simulation of a parking system with 4 parking spots and 3 entry gates. The system uses multithreading and semaphores to manage concurrent car arrivals and departures, ensuring thread-safe access to parking spots. Cars arrive at specified times, park for predetermined durations, and exit, with detailed logging and final statistics reported.

### Objectives
- **Thread Synchronization**: Implemented using semaphores to control access to parking spots.
- **Concurrency Management**: Handles multiple car threads across three gates without race conditions.
- **Simulation Realism**: Uses `Thread.sleep()` to simulate arrival times and parking durations.
- **Status Reporting**: Tracks and displays current parking status and total cars served.
