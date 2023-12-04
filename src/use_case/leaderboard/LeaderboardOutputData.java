package use_case.leaderboard;

import java.util.SortedMap;

/**
 * Class representing the output data for Leaderboard.
 */
public class LeaderboardOutputData {
    private SortedMap<Object, Object> leaderboard;

    /**
     * Is a constructor for a LeaderboardOutputData object.
     * @param leaderboard is a SortedMap object, containing the information of the leaderboard
     */
    public LeaderboardOutputData(SortedMap<Object, Object> leaderboard) {
        this.leaderboard = leaderboard;
    }

    /* ----- Getters and setters ----- */
    public SortedMap<Object, Object> getLeaderboard() {
        return leaderboard;
    }
}