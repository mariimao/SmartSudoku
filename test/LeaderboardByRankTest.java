import entity.leaderboard.LeaderboardByRank;
import entity.user.CommonUser;
import entity.user.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LeaderboardByRankTest {

    private LeaderboardByRank leaderboard;
    private Map<String, Integer> testerLeaderboard;

    @Before
    public void init() {
        testerLeaderboard = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            testerLeaderboard.put(Integer.toString(i), 0);
        }

        Map<String, User> accounts = new TreeMap<>();
        int numAccounts = (int) (Math.random() * 500);
        for (int i = 1; i <= numAccounts; i++) {
            Map<LocalTime, Integer> scores = new HashMap<>();
            int score = (int) (Math.random() * 5) - 1;
            scores.put(LocalTime.of(1, 2, 3), score);
            User user = new CommonUser("user" + i, "pwd" + i, scores);
            accounts.put("user" + i, user);
        }
        leaderboard = new LeaderboardByRank(accounts);
        System.out.println(leaderboard.getLeaderboard());
    }

//    private void putHighScoreInTester(int score, String username) {
//        int i = 0;
//        for (Map.Entry<String, Integer> entry : testerLeaderboard.entrySet()) {
//            if () {
//
//            }
//        }
//    }

    @Test
    public void testGetUserView() {
        System.out.println(leaderboard);
    }
}
