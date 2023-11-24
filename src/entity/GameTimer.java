package entity;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private long startTime;
    private long endTime;
    private long currentElapsedTime;
    private boolean gameStarted;

    public GameTimer(boolean gameStarted){
        this.gameStarted = gameStarted;
        this.startTime = 0;
        this.endTime = 0;
        this.currentElapsedTime = 0;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setStartTime(){
        startTime = System.currentTimeMillis();
    }

    public void setEndTime() {
        endTime = System.currentTimeMillis();
    }

    public void startGameTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 2*60*1000);
    }

    public void resetTime () {
        // is this allowed
        startTime = 0;
        endTime = 0;
    }

    public long getFinalElapsedTime(){
        // function might be redudant
        if (startTime != 0 || endTime != 0) {
            return endTime - startTime;
        }
        return startTime; // need to send info user
    }

    public ArrayList<Integer> getDisplayTimeData() {
        // double check which value is best to use
        currentElapsedTime = System.currentTimeMillis() - startTime;
        ArrayList<Integer> time = new ArrayList<Integer>();
        float currentElapsedSeconds = (float) currentElapsedTime / 1000; /// converts from
        Integer seconds = Math.round(currentElapsedSeconds % 60); // seconds rounded
        Integer minutes = Math.round(currentElapsedSeconds / 60);
        time.add(seconds); // seconds
        time.add(minutes); // minutes
        return time;
    }


}
