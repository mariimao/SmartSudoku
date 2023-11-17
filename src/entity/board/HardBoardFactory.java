package entity.board;

import entity.board.Board;
import entity.board.BoardFactory;
import entity.board.HardBoard;

public class HardBoardFactory implements BoardFactory {
    @Override
    public Board create() {return new HardBoard();}
}
