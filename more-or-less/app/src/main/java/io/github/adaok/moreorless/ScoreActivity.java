package io.github.adaok.moreorless;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.adaok.moreorless.core.GamePreferences;
import io.github.adaok.moreorless.core.models.Score;

public class ScoreActivity extends AppCompatActivity {

    private static final String TAG = "ScoreActivity";

    private ProgressBar mProgressBar;
    private TableLayout mTLScores;
    private TextView mTvScore1, mTvScore2, mTvScore3, mTvScore4, mTvScore5;
    private TextView mTvScoreName1, mTvScoreName2, mTvScoreName3, mTvScoreName4, mTvScoreName5;

    private List<Score> mScoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mProgressBar = findViewById(R.id.pb_score_aty);
        mTLScores = findViewById(R.id.tl_score_aty);
        mTvScoreName1 = findViewById(R.id.tv_name_score_1);
        mTvScoreName2 = findViewById(R.id.tv_name_score_2);
        mTvScoreName3 = findViewById(R.id.tv_name_score_3);
        mTvScoreName4 = findViewById(R.id.tv_name_score_4);
        mTvScoreName5 = findViewById(R.id.tv_name_score_5);
        mTvScore1 = findViewById(R.id.tv_value_score_1);
        mTvScore2 = findViewById(R.id.tv_value_score_2);
        mTvScore3 = findViewById(R.id.tv_value_score_3);
        mTvScore4 = findViewById(R.id.tv_value_score_4);
        mTvScore5 = findViewById(R.id.tv_value_score_5);

        Task task = new Task();
        task.execute();
    }

    // AsyncTask<Params, Progress, Result>
    private class Task extends AsyncTask<String, Integer, String> {

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mTLScores.setVisibility(View.GONE);
        }

        protected String doInBackground(String... params) {

            List<Score> scoreList = GamePreferences.getInstance(getBaseContext()).getScores();
            Comparator<Score> scoreComparator = new Comparator<Score>() {
                @Override
                public int compare(Score score, Score t1) {
                    return Integer.compare(score.getNbMoves(), t1.getNbMoves());
                }
            };
            Collections.sort(scoreList, scoreComparator);

            mScoreList = scoreList;

            return "result";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: ");

            if (mScoreList.size() >= 1) {
                mTvScoreName1.setText(mScoreList.get(0).getGamerName());
                mTvScore1.setText(String.valueOf(mScoreList.get(0).getNbMoves()));
            }

            if (mScoreList.size() >= 2) {
                mTvScoreName2.setText(mScoreList.get(1).getGamerName());
                mTvScore2.setText(String.valueOf(mScoreList.get(1).getNbMoves()));
            }

            if (mScoreList.size() >= 3) {
                mTvScoreName3.setText(mScoreList.get(2).getGamerName());
                mTvScore3.setText(String.valueOf(mScoreList.get(2).getNbMoves()));
            }

            if (mScoreList.size() >= 4) {
                mTvScoreName4.setText(mScoreList.get(3).getGamerName());
                mTvScore4.setText(String.valueOf(mScoreList.get(3).getNbMoves()));
            }

            if (mScoreList.size() >= 5) {
                mTvScoreName5.setText(mScoreList.get(4).getGamerName());
                mTvScore5.setText(String.valueOf(mScoreList.get(4).getNbMoves()));
            }

            mProgressBar.setVisibility(View.GONE);
            mTLScores.setVisibility(View.VISIBLE);

        }

    }
}
