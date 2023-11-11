package entity;


import java.time.LocalTime;
import java.util.*;

public class LeaderboardByHighScore implements Leaderboard {

    private final Map<String, User> accounts;
    private final SortedMap<Integer, Set<String>> leaderboard;


    public LeaderboardByHighScore(Map<String, User> accounts) {
        this.accounts = accounts;
        this.leaderboard = this.generateLeaderboard();
    }

    public SortedMap<Integer, Set<String>> getLeaderboard() {
        return this.leaderboard;
    }

    public SortedMap<Integer, Set<String>> generateLeaderboard() {
        Map<Integer, Set<String>> highScoreToUsers = this.highScoreToUsers();

        SortedMap<Integer, Set<String>> leaderboard = new TreeMap<>();

        Set<Integer> scores = this.highScoreToUsers().keySet();
        Integer highestScore = Collections.max(scores);
        for (int i = 1; i <= highScoreToUsers.size(); i++) {
            leaderboard.put(i, highScoreToUsers.get(highestScore));
            scores.remove(highestScore);
            if (!scores.isEmpty()) {
                highestScore = Collections.max(scores);
            }
        }
        return leaderboard;
    }

    private Map<Integer, Set<String>> highScoreToUsers() {
        Map<Integer, Set<String>> highScoreToUsers = new HashMap<>();
        for (String name : accounts.keySet()) {
            Integer highScore = Collections.max(accounts.get(name).getScores().values());
            if (highScoreToUsers.containsKey(highScore)) {
                highScoreToUsers.get(highScore).add(name);
            } else {
                Set<String> names = new HashSet<>();
                names.add(name);
                highScoreToUsers.put(highScore, names);
            }
        }
        return highScoreToUsers;
    }

    public static void main(String[] args) {
        Map<String, User> accounts = new HashMap<>();

        Map<LocalTime, Integer> sample = new HashMap<>();
        sample.put(LocalTime.of(1,2,3), 10);
        sample.put(LocalTime.of(1,2,5), 9);
        accounts.put("Spongebob", new CommonUser("Spongebob", "a", sample));

        Map<LocalTime, Integer> sample2 = new HashMap<>();
        sample2.put(LocalTime.of(1,2,3), 13);
        sample2.put(LocalTime.of(1,2,4), 12);
        accounts.put("Patrick", new CommonUser("Patrick", "a", sample2));

        Map<LocalTime, Integer> sample3 = new HashMap<>();
        sample3.put(LocalTime.of(1,2,3), 4);
        sample3.put(LocalTime.of(1,2,4), 5);
        sample3.put(LocalTime.of(1,2,5), 13);
        accounts.put("Squidward", new CommonUser("Squidward", "a", sample3));

        Leaderboard leaderboard = new LeaderboardByHighScore(accounts);

        System.out.println(leaderboard.generateLeaderboard());
    }
}
