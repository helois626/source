package com.ally.pam.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ally.pam.R;
import com.ally.pam.adapter.SpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Ally on 11/30/2015.
 */
public class RequestDetailActivity extends Activity implements View.OnClickListener {

    private ImageView ivBack;

    private Button btnPay;
    private Button btnChat;

    private Spinner spCardCategory;

    private String[] CARD;
    private ArrayList<String> arrayListGender;

    private ArrayList<String> arrayListCategory;
    private ImageButton ibtnAdd;

    private ImageButton ibtnCancel;

    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.request_detail);

        ivBack = (ImageView) findViewById(R.id.back_imageview);
        ivBack.setOnClickListener(this);

        btnPay = (Button) findViewById(R.id.request_pay_button);
        btnChat = (Button) findViewById(R.id.request_chat_button);
        btnPay.setOnClickListener(this);
        btnChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back_imageview:
                finish();
                break;
            case R.id.request_pay_button:
                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog);
                CARD = getResources().getStringArray(R.array.category);
                arrayListCategory = new ArrayList<>();
                for (int i = 0; i < CARD.length; i++) {
                    arrayListCategory.add(CARD[i]);
                }
                spCardCategory = (Spinner) dialog.findViewById(R.id.category_spinner);
                spCardCategory.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, arrayListCategory));

                ibtnCancel = (ImageButton) dialog.findViewById(R.id.cancel_Imagebutton);
                ibtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                TextView tvDate = (TextView) dialog.findViewById(R.id.date_textview);
                tvDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog datePicker1 = new Dialog(RequestDetailActivity.this);
                        datePicker1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        datePicker1.setCancelable(false);
                        datePicker1.setContentView(R.layout.date_picker);
                        Button btnDone = (Button) datePicker1.findViewById(R.id.done_button);
                        btnDone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker1.dismiss();
                            }
                        });
                        datePicker1.show();
                    }
                });
                dialog.show();
                break;
            case R.id.request_chat_button:
                Intent chat = new Intent(this, ChatActivity.class);
                startActivity(chat);
                break;
        }
    }
}
