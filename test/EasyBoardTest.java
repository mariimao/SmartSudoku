import entity.board.EasyBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EasyBoardTest {
    private EasyBoard easyboard;
    private int[][] solutionBoard;

    @Before
    public void init() {
        easyboard = new EasyBoard();
        int[][] solutionBoard = easyboard.getSolutionBoard();
    }

    /* TODO: WIP */
//    public void testValidBoard() {
//        boolean isValidBoard = true;
//        HashMap<Integer, Boolean>[][] currBoard = easyboard.getCurrBoard();
//        for(int i = 0; i < 4; i++){
//            if(!isValidRow(currBoard[i])){
//                isValidBoard = false;
//            }
//        }
//        for(i = 0; i < 9; i++){
//            if(!isValidColumn(currBoard, i)){
//                isValidBoard = false;
//            }
//        }
//        for(i = 0; i < 9; i += 3){
//            for(let j = 0; j < 9; j += 3){
//                if(!isValidCell(currBoard, i, j)){
//                    isValidBoard = false;
//                }
//            }
//        }
//    }
//
//    private boolean isValidRow(HashMap<Integer, Boolean>[] row) {
//        int checker = 0;
//        int num;
//        for (HashMap<Integer, Boolean> map : row) {
//            if (map.isEmpty()) {
//                num = row[i].get(i);
//                if((checker & (1 << num)) > 0)
//                    return false;
//                checker = (checker | (1 << num));
//            } else {
//                for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
//                }
//            }
//        }
//    }

    @Test
    public void testGenerateBlankBoard() {
        boolean allBlankValues = true;
        HashMap<Integer, Boolean>[][] blankBoard = easyboard.generateBlankBoard();
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                if (!blankBoard[i][j].isEmpty()) {
                    allBlankValues = false;
                }
            }
        }
        assertTrue(allBlankValues);
    }

    @Test
    public void testNoSpacesLeft() {
        HashMap<Integer, Boolean>[][] testBoard = new HashMap[4][4];
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                HashMap<Integer, Boolean> value = new HashMap<>();
                value.put(solutionBoard[i][j], true);
                testBoard[i][j] = value;
            }
        }
        easyboard.setBoard(testBoard);
        assertTrue(easyboard.noSpacesLeft());
    }
}
