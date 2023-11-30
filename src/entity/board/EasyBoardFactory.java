package entity.board;

/**
 * Factory for the creation of EasyBoard objects.
 */
public class EasyBoardFactory implements BoardFactory {

    /**
     * @return a new EasyBoard object
     */
    @Override
    public EasyBoard create() {
        return new EasyBoard();
    }
}
