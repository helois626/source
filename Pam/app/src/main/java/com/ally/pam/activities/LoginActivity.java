package com.ally.pam.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ally.pam.R;

/**
 * Created by Ally on 11/20/2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private ImageButton btnSignin;
    private ImageButton btnSignup;
    private ImageButton btnLinked;
    private ImageButton btnForgot;

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {
        btnSignin = (ImageButton) findViewById(R.id.lgbtn_signin);
        btnSignup = (ImageButton) findViewById(R.id.lgbtn_signup);
        btnForgot = (ImageButton) findViewById(R.id.btnforgot);
        btnSignin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnForgot.setOnClickListener(this);

        etUsername = (EditText) findViewById(R.id.etusername);
        etPassword = (EditText) findViewById(R.id.etpassword);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lgbtn_signin:
                Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(it);
                finish();
                break;

            case R.id.lgbtn_signup:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
