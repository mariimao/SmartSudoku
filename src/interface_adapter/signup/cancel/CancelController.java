package interface_adapter.signup.cancel;

import use_case.signup.cancel.CancelInputBoundary;

public class CancelController {

    private final CancelInputBoundary cancelInteractor;

    public CancelController(CancelInputBoundary cancelInteractor) {
        this.cancelInteractor = cancelInteractor;
    }
    public void execute() {
        cancelInteractor.execute();
    }
}
