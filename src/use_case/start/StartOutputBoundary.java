package use_case.start;

public interface StartOutputBoundary {

    void prepareSuccessView();

    void prepareFailView(String error);
}
