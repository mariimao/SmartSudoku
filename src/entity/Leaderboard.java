package entity;

import java.util.SortedMap;
import java.util.Set;

public interface Leaderboard {

    <K, V> SortedMap<K, V> getLeaderboard();
}
