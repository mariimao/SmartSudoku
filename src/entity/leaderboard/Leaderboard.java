package entity.leaderboard;

import java.util.SortedMap;
import java.util.Set;

public interface Leaderboard {

    <K, V> SortedMap<K, V> getLeaderboard();

    <K, V> SortedMap<K, V> getUserView(String name);
}