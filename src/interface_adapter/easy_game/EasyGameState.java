package interface_adapter.easy_game;

import entity.board.GameState;
import entity.user.User;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import java.util.LinkedList;

public class EasyGameState {
    private String easyGameError = "";
    private GameState easyGame = null;
    private int row = 0;
    private int column = 0;
    private int value = 0;
    private int difficulty = 1;


    public EasyGameState() {}
    public void setEasyGame(GameState gameState) {easyGame = gameState;}
    public void setEasyGameError(String e) {easyGameError = e;}
    public void setRow(int row) {this.row = row;}
    public void setColumn(int column) {this.column = column;}
    public void setValue(int value) {this.value = value;}

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public GameState getEasyGame() {return easyGame;}
    public String getEasyGameError() {return easyGameError;}
    public int getRow() {return row;}
    public int getColumn() {return column;}
    public int getValue() {return value;}
    public int getDifficulty() {return difficulty;}


}
