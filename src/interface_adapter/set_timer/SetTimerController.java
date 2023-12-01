package interface_adapter.set_timer;

import use_case.set_timer.SetTimerInputBoundary;
import use_case.set_timer.SetTimerInputData;

public class SetTimerController {

    final SetTimerInputBoundary setTimerInteractor;

    public SetTimerController(SetTimerInputBoundary setTimerInteractor) {
        this.setTimerInteractor = setTimerInteractor;
    }

    public void execute(long start_time) {
        SetTimerInputData setTimerInputData = new SetTimerInputData(start_time);
    }
}
