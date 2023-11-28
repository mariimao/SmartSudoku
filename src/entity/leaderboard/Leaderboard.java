package entity.leaderboard;

import java.util.SortedMap;

/**
 * Interface to construct leaderboard classes.
 */
public interface Leaderboard {

    <K, V> SortedMap<K, V> getUserView(String username);

    <K, V> SortedMap<K, V> getLeaderboard();
}
