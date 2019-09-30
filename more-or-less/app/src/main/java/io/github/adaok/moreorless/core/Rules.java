package io.github.adaok.moreorless.core;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Random;

import io.github.adaok.moreorless.core.models.Score;

public class Rules {

    private static final String TAG = "Rules";

    // Instance of the current class
    private static Rules instance;

    // Default constructor made private
    private Rules(){
    }

    public static Rules getInstance(){
        if(instance == null){
            instance = new Rules();
        }
        return instance;
    }

    /**
     * Method for check if secret number is equals, superior or inferior to number submit by user.
     * @param result the secret number
     * @param submit the number offers by the user.
     * @return The result enum value for display it to the user.
     */
    public Result getResultInComparaison(int result, int submit) {
        Log.d(TAG, "getResultInComparaison: ");
        // TODO: 2019-09-30 use ResultEnum class for this return.

    }

    public int getRandomNumber(int maxInt) {
        Log.d(TAG, "getRandomNumber: ");
        Random random = new Random();
        int value = random.nextInt(maxInt) + 1;
        return value;
    }

    /**
     * Check if the numbers moves for previous game can be saved in score screen.
     * @param context for call GamePreferences method.
     * @param nbMoves the number of moves for the previous game
     * @return true if the score can be saved, false otherwise.
     */
    public boolean checkIfScoreCanBeSaved(Context context, int nbMoves) {
        Log.d(TAG, "checkIfScoreCanBeSaved: ");
        List<Score> scoreList = GamePreferences.getInstance(context).getScores();

        // TODO: 2019-09-30 check first if all scores input are occupied
        //for this, check field in Constants class.

        // TODO: 2019-09-30 second part. Check all scores in scoreList object for see
        //if the nbMoves is inferior to a score saved. if it's the case, return true.

    }
}
