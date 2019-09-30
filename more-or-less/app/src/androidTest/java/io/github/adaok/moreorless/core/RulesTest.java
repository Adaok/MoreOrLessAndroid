package io.github.adaok.moreorless.core;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.github.adaok.moreorless.R;
import io.github.adaok.moreorless.core.models.Score;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RulesTest {

    private Context context;
    private String packageName = "io.github.adaok.moreorless";

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();

        assertEquals(packageName, context.getPackageName());
    }

    @Test
    public void getResultInComparaison() {
        int resultOne = 3;
        int resultTwo = 5;
        int resultThree = 13;
        int submitOne = 3;
        int submitTwo = 8;
        int submitThree = 11;

        assertEquals(Result.OK, Rules.getInstance().getResultInComparaison(resultOne, submitOne));
        assertEquals(Result.LESS, Rules.getInstance().getResultInComparaison(resultTwo, submitTwo));
        assertEquals(Result.MORE, Rules.getInstance().getResultInComparaison(resultThree, submitThree));
    }

    @Test
    public void checkIfScoreCanBeSaved() {
        assertEquals(packageName, context.getPackageName());
        Score score1 = new Score("Polo", 1);
        Score score2 = new Score("Michel", 1);
        Score score3 = new Score("John", 1);
        Score score4 = new Score("Thierry", 1);
        Score score5 = new Score("Ted", 2);
        Score score6 = new Score("Mickael", 6);
        Score score7 = new Score("Hugo", 10);

        assertTrue(GamePreferences.getInstance(context).saveScore(score1));
        assertTrue(GamePreferences.getInstance(context).saveScore(score2));
        assertTrue(GamePreferences.getInstance(context).saveScore(score3));
        assertTrue(GamePreferences.getInstance(context).saveScore(score4));
        assertTrue(GamePreferences.getInstance(context).saveScore(score5));

        assertTrue(Rules.getInstance().checkIfScoreCanBeSaved(context, score1.getNbMoves()));
        assertTrue(Rules.getInstance().checkIfScoreCanBeSaved(context, score2.getNbMoves()));
        assertTrue(Rules.getInstance().checkIfScoreCanBeSaved(context, score3.getNbMoves()));

        assertFalse(Rules.getInstance().checkIfScoreCanBeSaved(context, score6.getNbMoves()));
        assertFalse(Rules.getInstance().checkIfScoreCanBeSaved(context, score7.getNbMoves()));
    }
}