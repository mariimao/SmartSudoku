package entity;

public class EasyBoardFactory implements BoardFactory {
    @Override
    public Board create() {
        return new EasyBoard();
    }
}
