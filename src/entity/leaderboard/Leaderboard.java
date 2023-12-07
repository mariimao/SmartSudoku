package entity.leaderboard;

import java.util.SortedMap;

/**
 * Interface to construct leaderboard classes.
 */
public interface Leaderboard {

    /**
     * Generates the information for where a user's position is on the leaderboard.
     * <p>
     *
     * @param username provided username
     * @return SortedMap object storing the username and its corresponding rank
     */
    <K, V> SortedMap<K, V> getUserView(String username);

    /**
     * Helper function for the constructor.
     * This method returns a leaderboard with the highest scores and their corresponding
     * users, and returns them in a SortedMap object.
     * <p>
     *
     * @return A SortedMap containing the leaderboard.
     */
    <K, V> SortedMap<K, V> getLeaderboard();
}
