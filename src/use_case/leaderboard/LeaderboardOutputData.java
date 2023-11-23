package use_case.leaderboard;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;

public class LeaderboardOutputData {
    private SortedMap<Object, Object> leaderboard;


    public LeaderboardOutputData(SortedMap<Object, Object> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public String getLeaderboard() {
        return getLeaderboard().toString();
    }
}