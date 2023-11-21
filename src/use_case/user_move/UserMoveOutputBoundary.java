package use_case.user_move;

public interface UserMoveOutputBoundary {

    void prepareSuccessView (UserMoveOutputData userMoveOutputData);

    void prepareFailView (String error);

    void prepareEndView (UserMoveOutputData userMoveOutputData);
}
