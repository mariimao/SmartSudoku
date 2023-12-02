package entity.board;

/**
 * Interface for BoardFactory, used to create new Board objects.
 * Classes that implement this interface are EasyBoardFactory and HardBoardFactory.
 */
public interface BoardFactory {

    /**
     * @return a new Board object
     */
    Board create();
    Board create(String positions);
}
