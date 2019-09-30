package io.github.adaok.moreorless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";

    private Button mStartBtn;
    private Button mScoresBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mStartBtn = findViewById(R.id.btn_start_game_aty_home);
        mScoresBtn = findViewById(R.id.btn_scores_game_aty_home);

        mStartBtn.setOnClickListener(this);
        mScoresBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_start_game_aty_home:
                Intent intent = new Intent(this, GameActivity.class );
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Start game !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_scores_game_aty_home:
                Intent intentScores = new Intent(this, ScoreActivity.class );
                startActivity(intentScores);
            default:
                Log.w(TAG, "onClick: case not defined.");
        }
    }
}
