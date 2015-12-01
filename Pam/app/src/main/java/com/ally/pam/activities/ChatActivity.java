package com.ally.pam.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ally.pam.R;

/**
 * Created by Ally on 11/30/2015.
 */
public class ChatActivity extends Activity implements View.OnClickListener {

    private TextView tvDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initUI();
    }

    private void initUI() {
        tvDone = (TextView) findViewById(R.id.done_textview);
        tvDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.done_textview:
                finish();
                break;
        }
    }
}
