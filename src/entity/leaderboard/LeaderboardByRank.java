package entity.leaderboard;

import entity.user.CommonUser;
import entity.user.User;

import java.time.LocalTime;
import java.util.*;

/**
 * Generates a leaderboard by rank based on all the user data.
 */
public class LeaderboardByRank implements Leaderboard {

    private final Map<String, User> accounts; // String username, User user
    private final SortedMap<Integer, Set<String>> leaderboard; // Integer score, Set<String> usernames

    /**
     * Constructor for LeaderboardByRank. Initializes the private variables accounts
     * and leaderboard.
     * @param accounts Map containing a String of the username and its corresponding
     *                 User object.
     */
    public LeaderboardByRank(Map<String, User> accounts) {
        this.accounts = accounts;
        this.leaderboard = this.generateLeaderboard();
    }

    /**
     * Generates the information for where a user's position is on the leaderboard.
     * <p>
     * @param username  provided username
     * @return          SortedMap object storing the username and its corresponding rank
     */
    public SortedMap<Integer, String> getUserView(String username) {
        SortedMap<Integer, String> userInfo = new TreeMap<>();
        Integer rank = 0;
        for (Integer i : leaderboard.keySet()) {
            if (leaderboard.get(i).contains(username)) {
                rank = i;
            }
        }

        userInfo.put(rank, username);
        return userInfo;
    }

    /**
     * Helper function for the constructor.
     * This method returns a leaderboard with the highest scores and their corresponding
     * users, and returns them in a SortedMap object.
     * <p>
     * @return      A SortedMap containing the leaderboard.
     */
    private SortedMap<Integer, Set<String>> generateLeaderboard() {
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

    /**
     * Helper function for generateLeaderBoard().
     * This method finds the highest score for each user, and then returns them in a
     * Map collection. If there are multiple users with the same highest score,
     * multiple names are stored in a Set under that score.
     * <p>
     * @return      A Map of each user's highest score.
     */
    private Map<Integer, Set<String>> highScoreToUsers() {
        Map<Integer, Set<String>> highScoreToUsersMap = new HashMap<>();
        for (String name : accounts.keySet()) {
            Map<LocalTime, Integer> timeScores = accounts.get(name).getScores();

            if (timeScores.size()!=0) {
                List scores = new ArrayList<>();
                for (Integer i : timeScores.values()) {
                    scores.add(i.intValue());
                }

                int highScore = (int) Collections.max(scores);
                if (highScoreToUsersMap.containsKey(highScore)) {
                    highScoreToUsersMap.get(highScore).add(name);
                } else {
                    Set<String> names = new HashSet<>();
                    names.add(name);
                    highScoreToUsersMap.put(highScore, names);
                }
            }
        }
        return highScoreToUsersMap;
    }

    /* ----- Getters and setters ----- */
    public SortedMap<Integer, Set<String>> getLeaderboard() {
        return leaderboard;
    }

    // TODO: Delete before submitting
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

        Leaderboard leaderboard = new LeaderboardByRank(accounts);

        System.out.println(leaderboard.getLeaderboard());
    }
}
