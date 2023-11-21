package entity.board;

import entity.board.Board;
import entity.board.BoardFactory;
import entity.board.EasyBoard;

public class EasyBoardFactory implements BoardFactory {
    @Override
    public Board create() {
        return new EasyBoard();
    }
}
