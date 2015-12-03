package com.ally.pam.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ally.pam.R;
import com.ally.pam.adapter.ChatAdapter;

/**
 * Created by Ally on 11/30/2015.
 */
public class ChatActivity extends Activity implements View.OnClickListener {

    private TextView tvDone;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initUI();
    }

    private void initUI() {
        tvDone = (TextView) findViewById(R.id.done_textview);
        tvDone.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.chat_listview);
        listView.setAdapter(new ChatAdapter(this));
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
