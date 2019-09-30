package io.github.adaok.moreorless.core.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    private Score scoreOne;
    private Score scoreTwo;

    public static final String NAME = "Polo";
    public static final String NAME_TWO = "Marco";

    @Before
    public void setUp() {
        scoreOne = new Score(NAME, 5);
        scoreTwo = new Score(NAME_TWO, 3);
    }

    @Test
    public void getGamerName() {
        assertNotNull(scoreOne);
        assertNotNull(scoreTwo);
        assertEquals(NAME, scoreOne.getGamerName());
        assertEquals(NAME_TWO, scoreTwo.getGamerName());
    }

    @Test
    public void getNbMoves() {
        assertNotNull(scoreOne);
        assertNotNull(scoreTwo);
        assertEquals(5, scoreOne.getNbMoves());
        assertEquals(3, scoreTwo.getNbMoves());
    }

}