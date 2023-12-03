package interface_adapter.make_move;

import entity.board.GameState;
import entity.user.User;

public class MakeMoveState {
    private GameState gameBeingPlayed = null;
    private int value = 0;
    private int row = 0;
    private int col = 0;
    private boolean gameFinshedLost = false;
    private boolean gameFinishedWin = false;
    private boolean userInputWrong = false;


    public MakeMoveState() {}
    public void setGameBeingPlayed(GameState gameState) { gameBeingPlayed = gameState;}
    public GameState getGameBeingPlayed() {return gameBeingPlayed;}
    public void setValue(int value) {this.value = value;}
    public int getValue() {return value;}

    public void setRow(int row) {this.row = row;}
    public int getRow() {return row;}

    public void setCol(int col) {this.col = col;}
    public int getCol() {return col;}

    public boolean isGameFinishedWin() {return gameFinishedWin;}

    public boolean isGameFinshedLost() {return gameFinshedLost;}

    public void setGameFinishedWin(boolean gameFinishedWin) {this.gameFinishedWin = gameFinishedWin;}

    public void setGameFinishedLost(boolean gameFinshedLost) {this.gameFinshedLost = gameFinshedLost;}

    public void setUserInputWrong(boolean userInputWrong) {this.userInputWrong = userInputWrong;}

    public boolean isUserInputWrong() {return userInputWrong;}
}
