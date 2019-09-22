package com.res.cloudspot.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUILatestVisit;
import com.res.cloudspot.MainActivity;

public class LauncherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        Intent intent = QMUILatestVisit.intentOfLatestVisit(this);
        if (intent == null) {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
