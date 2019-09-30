package io.github.adaok.moreorless.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.adaok.moreorless.BuildConfig;
import io.github.adaok.moreorless.core.models.Score;

public class GamePreferences {

    private static final String TAG = "GamePreferences";

    private SharedPreferences mPreferences;

    // Instance of the current class
    private static GamePreferences instance;

    // Default constructor made private
    private GamePreferences(Context context){
        mPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

    public static GamePreferences getInstance(Context context){
        if(instance == null){
            instance = new GamePreferences(context);
        }
        return instance;
    }

    private HashMap<String, Integer> getProtectedScores() {
        Log.d(TAG, "getProtectedScores: ");
        HashMap<String, Integer> map = new HashMap<>();

        Map<String, ?> allEntries = mPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith(Constants.PREFIX_SCORE)) {
                map.put(entry.getKey(), (Integer) entry.getValue());
            }
        }

        return map;
    }

    /**
     * Return list of all scores not sorted.
     * @return
     */
    public List<Score> getScores() {
        Log.d(TAG, "getScores: ");
        List<Score> scoreList = new ArrayList<>();
        HashMap<String, Integer> protectedScores = getProtectedScores();
        for (HashMap.Entry<String, Integer> entry : protectedScores.entrySet()) {
            String key = removePrefix(entry.getKey());
            Score scoreToInsert = new Score(key, entry.getValue());
            scoreList.add(scoreToInsert);
        }

        return scoreList;
    }

    public boolean saveScore(Score score) {
        Log.d(TAG, "saveScore: ");

        Score lowerScore = null;
        HashMap<String, Integer> protectedScores = getProtectedScores();

        for (HashMap.Entry<String, Integer> entry : protectedScores.entrySet()) {
            Score scoreReference = new Score(entry.getKey(), entry.getValue());
            if (lowerScore != null) {
                if (lowerScore.getNbMoves() < scoreReference.getNbMoves()) {
                    lowerScore = scoreReference;
                }
            } else {
                lowerScore = scoreReference;
            }
        }

        if (protectedScores.size() < Constants.LIMIT_SCORE_SAVED || score.getNbMoves() < lowerScore.getNbMoves()) {
            SharedPreferences.Editor editor = mPreferences.edit();
            if (lowerScore != null && protectedScores.size() >= Constants.LIMIT_SCORE_SAVED) {
                editor.remove(Constants.PREFIX_SCORE + lowerScore.getGamerName());
            }
            editor.putInt(Constants.PREFIX_SCORE + score.getGamerName(), score.getNbMoves());
            editor.apply();
            return true;
        }

        return false;
    }

    private String removePrefix(String value) {
        Log.d(TAG, "removePrefix: with value + " + value);
        String[] split = value.split(Constants.PREFIX_SCORE);
        return split[1];
    }

}
