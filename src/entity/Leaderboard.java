package entity;

import java.util.SortedMap;
import java.util.Set;

public interface Leaderboard {

    SortedMap<Integer, Set<String>> generateLeaderboard();
}
