package use_case.get_time;

import entity.GameTimer;

public class GetTimeInteractor implements GetTimeInputBoundary {

    final GetTimeOutputBoundary getTimePresenter;

    public GetTimeInteractor(GetTimeOutputBoundary getTimePresenter) {
        this.getTimePresenter = getTimePresenter;
    }
    @Override
    public void execute() {

    }
}
