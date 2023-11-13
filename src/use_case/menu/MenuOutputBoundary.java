package use_case.menu;

public interface MenuOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
