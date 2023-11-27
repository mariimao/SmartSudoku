/* TODO: This file does not run tests properly because it's an interface. Will resolve. */

import org.junit.Test;

public interface BoardTest {

    @Test
    default void testValidBoard(){}

    @Test
    default void testIsValidSolutions(){}

    @Test
    default void testGenerateBlankBoard(){}

    @Test
    default void testMakeMove(){}

    @Test
    default void testNoSpacesLeft(){}
}
