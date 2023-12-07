package entity;

/**
 * The Scores entity. Algorithm for calculating scores.
 */
public class Scores {

    private int scores;

    /**
     * Creates a score object
     * @param timer_score   the time left
     * @param board_deduct  how much of the board is left
     * @param lives_left    the amount of lives left
     * @param isCompleted   whether they completed it or not
     */
    public Scores(int timer_score, int board_deduct, int lives_left, boolean isCompleted) {

        int current_score = 400;
        if (isCompleted) {
            current_score = 400;
        } else {
            current_score -= 100;// players lose 100 points if they don't complete the game
            // deduct by how much time is left
            current_score = current_score - board_deduct;
            // deduct by how many lives are left
            current_score = current_score - (lives_left * 50);
            // multiply by how much time is left
            current_score = current_score + timer_score;
        }
        this.scores = current_score;
    }

    /**
     * The default constructor for a score
     */
    public Scores() {
    }

    /**
     *
     * @return scores
     */
    public int getScores() {
        return this.scores;
    }

    /**
     * Sets a new score
     * @param timer_score   the time left
     * @param board_deduct  how much of the board is left
     * @param lives_left    the amount of lives left
     * @param isCompleted   whether they completed it or not
     */
    public void setScores(int timer_score, int board_deduct, int lives_left, boolean isCompleted) {
        int current_score = 400;
        // if board is completed
        if (isCompleted) {
            current_score = 400;
        } else {
            current_score -= 100; // players lose 100 points if they don't complete the game
            // deduct by how much time is left
            current_score = current_score - board_deduct;
            // deduct by how many lives are left
            current_score = current_score - (lives_left * 50);
            // multiply by how much time is left
            current_score = current_score + timer_score;
        }
        this.scores = current_score;
    }
}