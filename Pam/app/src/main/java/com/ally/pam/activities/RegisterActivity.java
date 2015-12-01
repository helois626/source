package com.ally.pam.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ally.pam.R;

/**
 * Created by Ally on 11/20/2015.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRetype;

    private ImageButton btnSignUp;
    private ImageButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        initUI();
    }

    private void initUI() {
        btnSignUp = (ImageButton) findViewById(R.id.sign_up_button);
        btnSignIn = (ImageButton) findViewById(R.id.signin_button);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sign_up_button:
                break;

            case R.id.signin_button:
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(it);
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
