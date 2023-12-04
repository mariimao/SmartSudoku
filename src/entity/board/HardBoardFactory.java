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
    public HardBoard create() {
        return new HardBoard();
    }

    @Override
    public HardBoard create(String positions) {
        return new HardBoard(positions);
    }
}
