/* TODO: This file does not run tests properly because it's an interface. Will resolve. */

import org.junit.Before;
import org.junit.Test;
public interface SudokuScramblerTest {

    @Before
    default void init(){};

    @Test
    void testValidBoardAfterScramble();

    @Test
    void testValidSolutionsAfterScramble();

    @Test
    void testScrambledBoardMatchesSolution();
}
