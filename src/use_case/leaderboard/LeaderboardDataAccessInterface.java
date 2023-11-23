package use_case.leaderboard;

import data_access.UserDAO;
import entity.user.User;

import java.util.Map;

public interface LeaderboardDataAccessInterface {
    boolean existsByName(String username);
    Map<String, User> getAccounts();
}