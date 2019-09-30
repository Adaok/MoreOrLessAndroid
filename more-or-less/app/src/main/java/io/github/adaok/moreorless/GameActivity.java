package io.github.adaok.moreorless;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.adaok.moreorless.core.GamePreferences;
import io.github.adaok.moreorless.core.Result;
import io.github.adaok.moreorless.core.Rules;
import io.github.adaok.moreorless.core.models.Score;
import io.github.adaok.moreorless.ui_tools.CustomAlertDialogBuilder;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GameActivity";

    private EditText mETValue;
    private TextView mTVResult;
    private Button mBtnSubmit;

    private int mSecretNumber = 0;
    private int mCounterTry = 0;

    private boolean mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mETValue = findViewById(R.id.et_game_aty);
        mTVResult = findViewById(R.id.tv_game_aty);
        mBtnSubmit = findViewById(R.id.btn_submit_game_aty);

        mBtnSubmit.setOnClickListener(this);

        mSecretNumber = Rules.getInstance().getRandomNumber(20);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_submit_game_aty:
                if (mFinish) {
                    finish();
                    break;
                }
                int value = Integer.parseInt(mETValue.getText().toString());
                if (value != 0) {
                    mCounterTry++;
                    Result result = Rules.getInstance().getResultInComparaison(mSecretNumber, value);
                    setTextViewWithResult(result);
                } else {
                    Toast.makeText(GameActivity.this, getText(R.string.toast_retry), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void setTextViewWithResult(Result result) {
        Log.d(TAG, "setTextViewWithResult: ");
        switch (result) {
            case LESS:
                mTVResult.setText(getResources().getString(R.string.tv_less));
                Toast.makeText(GameActivity.this, getText(R.string.text_try_again), Toast.LENGTH_SHORT).show();
                break;
            case MORE:
                mTVResult.setText(getResources().getString(R.string.tv_more));
                Toast.makeText(GameActivity.this, getText(R.string.text_try_again), Toast.LENGTH_SHORT).show();
                break;
            case OK:
                String value = String.format(getResources().getString(R.string.tv_good_result), mCounterTry);
                mTVResult.setText(value);
                mFinish = true;
                displayPopupIfNewScore();
                break;
            default:
                mTVResult.setText("");
                break;

        }
        mETValue.getText().clear();
    }

    private void displayPopupIfNewScore() {
        Log.d(TAG, "displayPopupIfNewScore: ");
        if (Rules.getInstance().checkIfScoreCanBeSaved(getBaseContext(), mCounterTry)) {
            final CustomAlertDialogBuilder builder = new CustomAlertDialogBuilder(this, R.layout.dialog_title, R.layout.dialog_save_score);
            builder.setTitle("Congratulations !")
                    .setMessage(R.string.tv_dialog_congrats)
                    .setKeyboardType(InputType.TYPE_CLASS_TEXT)
                    .setPositiveButton(R.string.btn_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(GameActivity.this, "saved", Toast.LENGTH_SHORT).show();
                            String name = builder.getInputValue();
                            Score score = new Score(name, mCounterTry);
                            GamePreferences.getInstance(getBaseContext()).saveScore(score);
                            builder.closeKeyboard();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            builder.closeKeyboard();
                        }
                    })
                    .create().show();
        }
        mBtnSubmit.setText(R.string.btn_return_at_menu);
    }
}
