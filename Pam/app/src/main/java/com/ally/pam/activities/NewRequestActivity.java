package com.ally.pam.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.ally.pam.R;
import com.ally.pam.adapter.SpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Ally on 11/25/2015.
 */
public class NewRequestActivity extends Activity implements View.OnClickListener {

    private ImageButton ibtnClose;

    private Spinner spCity;
    private Spinner spCategory;

    private EditText etContent;

    private ArrayList<String> arrayListCity;
    private ArrayList<String> arrayListCategory;

    private String[] city;
    private String[] category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_request);

        initUI();

        initSpinner();
    }

    private void initUI() {
        ibtnClose = (ImageButton) findViewById(R.id.close_imagebutton);
        ibtnClose.setOnClickListener(this);

        spCity = (Spinner) findViewById(R.id.city_spinner);
        spCategory = (Spinner) findViewById(R.id.category_spinner);

        etContent = (EditText) findViewById(R.id.content_edittext);
    }

    private void initSpinner() {
        city = getResources().getStringArray(R.array.select_city);
        arrayListCity = new ArrayList<>();
        for (int i = 0; i < city.length; i++) {
            arrayListCity.add(city[i]);
        }
        spCity.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, arrayListCity));

        category = getResources().getStringArray(R.array.select_category);
        arrayListCategory = new ArrayList<>();
        for (int i = 0; i < category.length; i++) {
            arrayListCategory.add(category[i]);
        }
        spCategory.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, arrayListCategory));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.close_imagebutton:
                finish();
                break;
        }
    }
}
