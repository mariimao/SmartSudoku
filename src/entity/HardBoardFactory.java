package entity;

public class HardBoardFactory implements BoardFactory{
    @Override
    public Board create() {return new HardBoard();}
}
