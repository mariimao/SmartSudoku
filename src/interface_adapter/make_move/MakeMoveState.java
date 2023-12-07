package interface_adapter.make_move;

import entity.board.GameState;
import entity.user.User;

/**
 *  The state of MakeMove
 */
public class MakeMoveState {
    private GameState gameBeingPlayed = null;
    private int value = 0;
    private int row = 0;
    private int col = 0;

    /**
     * Constuctor for MakeMoveState
     */
    public MakeMoveState() {}

    // Below are the getters and setters
    public void setGameBeingPlayed(GameState gameState) { gameBeingPlayed = gameState;}
    public GameState getGameBeingPlayed() {return gameBeingPlayed;}
    public void setValue(int value) {this.value = value;}
    public int getValue() {return value;}
    public void setRow(int row) {this.row = row;}
    public int getRow() {return row;}
    public void setCol(int col) {this.col = col;}
    public int getCol() {return col;}



}
