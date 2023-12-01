package interface_adapter.get_time;

import use_case.get_time.GetTimeInputBoundary;

public class GetTimeController {

    final GetTimeInputBoundary getTimeInteractor;

    public GetTimeController (GetTimeInputBoundary getTimeInteractor) {
        this.getTimeInteractor = getTimeInteractor;
    }
    public void execute() {
        getTimeInteractor.execute();
    }
}
