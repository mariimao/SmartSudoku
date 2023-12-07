package entity;

public class Scores {

    private int scores;
    private int timer_score;
    private int board_deduct;
    private int lives_left;
    private boolean isCompleted;

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

    public Scores() {
    }

    public int getScores() {
        return this.scores;
    }

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