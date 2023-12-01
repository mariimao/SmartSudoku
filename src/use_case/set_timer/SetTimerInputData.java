package use_case.set_timer;

public class SetTimerInputData {
    private final long startTime;

    public SetTimerInputData(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }
}
