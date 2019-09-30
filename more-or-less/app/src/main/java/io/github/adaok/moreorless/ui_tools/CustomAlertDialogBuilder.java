package io.github.adaok.moreorless.ui_tools;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import io.github.adaok.moreorless.R;

public class CustomAlertDialogBuilder extends AlertDialog.Builder {

    private static final String TAG = "CustomAlertDialogBuildr";

    //region UI
    private TextView mTVTitleDialog;
    private TextView mTVDialog;
    private EditText mETDialog;
    //endregion

    public CustomAlertDialogBuilder(Context context) {
        super(context);
        Log.d(TAG, "CustomAlertDialogBuilder: ");
    }

    public CustomAlertDialogBuilder(Context context, @Nullable @LayoutRes Integer titleLayout, @Nullable @LayoutRes Integer contentLayout) {
        super(context);
        Log.d(TAG, "CustomAlertDialogBuilder: ");

        if (titleLayout == null) {
            titleLayout = R.layout.dialog_title;
        }
        if (contentLayout == null) {
            contentLayout = R.layout.dialog_save_score;
        }

        init(titleLayout, contentLayout);

    }

    private void init(@LayoutRes int titleLayout, @LayoutRes int contentLayout) {
        Log.d(TAG, "init: ");
        View titleDialogView = LayoutInflater.from(getContext()).inflate(titleLayout, null, false);
        mTVTitleDialog = titleDialogView.findViewById(R.id.tv_dialog_title);
        setCustomTitle(titleDialogView);

        View contentDialogView = LayoutInflater.from(getContext()).inflate(contentLayout, null, false);
        mTVDialog = contentDialogView.findViewById(R.id.tv_dialog_save_score);
        mETDialog = contentDialogView.findViewById(R.id.et_dialog_save_name);
        setView(contentDialogView);
    }

    //region Components Dialog

    @Override
    public CustomAlertDialogBuilder setTitle(int titleId) {
        Log.d(TAG, "setTitle: ");
        return setTitle(getContext().getResources().getString(titleId));
    }

    @Override
    public CustomAlertDialogBuilder setTitle(@Nullable CharSequence title) {
        Log.d(TAG, "setTitle: ");
        mTVTitleDialog.setText(title);
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setMessage(int messageId) {
        Log.d(TAG, "setMessage: ");
        return setMessage(getContext().getResources().getString(messageId));
    }

    @Override
    public CustomAlertDialogBuilder setMessage(@Nullable CharSequence message) {
        Log.d(TAG, "setMessage: ");
        mTVDialog.setText(message);
        return this;
    }

    public CustomAlertDialogBuilder setEditText(@Nullable CharSequence email) {
        Log.d(TAG, "setEditText: ");
        if (mETDialog != null) {
            mETDialog.setText(email);
            mETDialog.setVisibility(View.VISIBLE);
            mETDialog.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
        return this;
    }

    public CustomAlertDialogBuilder setKeyboardType(int inputType) {
        Log.d(TAG, "setKeyboardType: ");
        mETDialog.setInputType(inputType);
        return this;
    }

    public CustomAlertDialogBuilder closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mETDialog.getWindowToken(), 0);
        return this;
    }

    public String getInputValue() {
        Log.d(TAG, "getInputValue: ");
        if (mETDialog != null) {
            return mETDialog.getText().toString();
        }
        return null;
    }

    //endregion


}
