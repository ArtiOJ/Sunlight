package dev.artifabiran.sunlight;

public class TimeController {
    private long tickCounter;

    public TimeController(long tickCounter) {
        this.tickCounter = tickCounter;
    }
    public long getTickCounter() {
        return tickCounter;
    }

    public double calculateDayMultiplier() {
        return 0.0;
    }

    public void setTickCounter(long tickCounter) {
        this.tickCounter = tickCounter;
    }
}

