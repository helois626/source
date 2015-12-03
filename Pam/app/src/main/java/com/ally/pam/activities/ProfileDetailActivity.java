package com.ally.pam.activities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ally.pam.R;
import com.ally.pam.adapter.GenderSpinnerAdapter;
import com.ally.pam.adapter.SpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Ally on 11/26/2015.
 */
public class ProfileDetailActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    private TextView tvCancel;
    private TextView tvSave;
    private TextView tvBirth;
    private TextView tvDate;

    private Spinner spGender;
    private Spinner spCardCategory;
    private String[] GENDER;

    private String[] CARD;
    private ArrayList<String> arrayListGender;

    private ArrayList<String> arrayListCategory;
    private Button ibtnAdd;

    private ImageButton ibtnCancel;

    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_detail);

        initUI();
    }

    private void initUI() {
        tvCancel = (TextView) findViewById(R.id.cancel_textview);
        tvSave = (TextView) findViewById(R.id.save_textview);
        tvBirth = (TextView) findViewById(R.id.birth_textview);
        tvCancel.setOnClickListener(this);
        tvCancel.setOnTouchListener(this);
        tvSave.setOnClickListener(this);
        tvSave.setOnTouchListener(this);
        tvBirth.setOnClickListener(this);

        GENDER = getResources().getStringArray(R.array.gender);
        arrayListGender = new ArrayList<>();
        for (int i = 0; i < GENDER.length; i++) {
            arrayListGender.add(GENDER[i]);
        }
        spGender = (Spinner) findViewById(R.id.gender_spinner);
        spGender.setAdapter(new GenderSpinnerAdapter(this, R.layout.gender_spinner_item, arrayListGender));

        ibtnAdd = (Button) findViewById(R.id.payment_add_imagebutton);
        ibtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.cancel_textview:
                finish();
                break;
            case R.id.save_textview:
                break;
            case R.id.payment_add_imagebutton:
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
                        final Dialog datePicker1 = new Dialog(ProfileDetailActivity.this);
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
            case R.id.birth_textview:
                final Dialog datePicker = new Dialog(this);
                datePicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
                datePicker.setCancelable(false);
//                datePicker.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                datePicker.setContentView(R.layout.date_picker);
                btnDone = (Button) datePicker.findViewById(R.id.done_button);
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePicker.dismiss();
                    }
                });
                datePicker.show();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        int id = v.getId();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                switch (id) {
                    case R.id.save_textview:
                        tvSave.setTextColor(getResources().getColor(R.color.menu_back));
                        break;
                    case R.id.cancel_textview:
                        tvCancel.setTextColor(getResources().getColor(R.color.menu_back));
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                switch (id) {
                    case R.id.save_textview:
                        tvSave.setTextColor(getResources().getColor(R.color.textColorPrimary));
                        break;
                    case R.id.cancel_textview:
                        tvCancel.setTextColor(getResources().getColor(R.color.textColorPrimary));
                        break;
                }
                break;
        }
        return false;
    }
}
