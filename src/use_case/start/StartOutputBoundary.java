package use_case.start;

public interface StartOutputBoundary {

    void prepareSuccessView(StartOutputData startOutputData);

    void prepareFailView(String error);
}
