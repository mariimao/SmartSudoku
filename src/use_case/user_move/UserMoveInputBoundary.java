package use_case.user_move;

import java.io.IOException;

public interface UserMoveInputBoundary {
    void execute(UserMoveInputData userMoveInputData) throws IOException;
}