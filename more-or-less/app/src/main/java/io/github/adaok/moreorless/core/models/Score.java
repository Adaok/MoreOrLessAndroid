package io.github.adaok.moreorless.core.models;

public class Score {

    private static final String TAG = "Score";

    private String mGamerName;
    private int mNbMoves;

    public Score(String gamerName, int nbMoves) {
        // TODO: 2019-09-30 Add security on set mNbMoves attribute.
    }

    public String getGamerName() {
        return mGamerName;
    }

    public void setGamerName(String gamerName) {
        mGamerName = gamerName;
    }

    public int getNbMoves() {
        return mNbMoves;
    }

    public void setNbMoves(int nbMoves) {
        // TODO: 2019-09-30 Add security on set mNbMoves attribute.
    }
}
