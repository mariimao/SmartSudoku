import app.EndGameUseCaseFactory;
import data_access.UserDAO;
import database.UserDAOTest;
import interface_adapter.end_game.EndGamePresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.UseCaseTestObjects;
import use_case.end_game.EndGameInteractor;
import view.EndGameView;

public class EndGameUseCaseTest {

    private EndGameInteractor endGameInteractor;
    @Before
    public void init() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAOTest().getUserDAO();
        EndGameView endGameView = EndGameUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getEndGameViewModel(), userDAO, useCaseTestObjects.getMenuViewModel(),
                useCaseTestObjects.getLeaderboardViewModel(), useCaseTestObjects.getStartViewModel(),
                useCaseTestObjects.getSignupViewModel(), useCaseTestObjects.getLoginViewModel());
        EndGamePresenter endGamePresenter = new EndGamePresenter(useCaseTestObjects.getLeaderboardViewModel(),
                useCaseTestObjects.getMenuViewModel(), useCaseTestObjects.getEndGameViewModel(),
                useCaseTestObjects.getViewManagerModel());
        endGameInteractor = new EndGameInteractor(userDAO, endGamePresenter);
    }

    @Test
    public void textExecute() {
    }
}

