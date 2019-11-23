package io.github.adaok.moreorless.core.models;

public class Score {

    private static final String TAG = "Score";

    private String mGamerName;
    private int mNbMoves;

    public Score(String gamerName, int nbMoves) {
        mGamerName = gamerName;
        if (nbMoves > 0) {
            mNbMoves = nbMoves;
        }
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
        if (nbMoves > 0) {
            mNbMoves = nbMoves;
        }
    }
}
