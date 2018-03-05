package edu.utep.cs.cs4330.timer;

public class TimerModel {
    protected static boolean isRunning = false;
    private long startTime;

    public void start()  {
        isRunning = true;
        startTime = System.currentTimeMillis();
        System.out.println("start clicked");
    }
    public void stop()  {
        isRunning = false;
        startTime = elapsedTime ();
        System.out.println("stop clicked");
    }
    public boolean isRunning ()  {
        return isRunning;
    }
    public long elapsedTime ()  {
        return System.currentTimeMillis() - startTime;
    }
    public long startTime () {
        return startTime;
    }

    public void setStartTime (long startTime) {
        this.startTime = startTime;
        isRunning = true;
    }
}