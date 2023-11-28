package entity.board;

import entity.board.Board;
import entity.board.BoardFactory;
import entity.board.HardBoard;

/**
 * Factory for the creation of HardBoard objects.
 */
public class HardBoardFactory implements BoardFactory {

    /**
     * @return a new HardBoard object
     */
    @Override
    public Board create() {
        return new HardBoard();
    }
}
