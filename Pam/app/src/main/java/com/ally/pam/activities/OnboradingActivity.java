package com.ally.pam.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;

import com.ally.pam.R;

/**
 * Created by Ally on 12/2/2015.
 */
public class OnboradingActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Gallery imageSwitcher;

    private Button btnStart;

    private ImageView ivFist;
    private ImageView ivSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        imageSwitcher = (Gallery) findViewById(R.id.imageSwitcher);
        imageSwitcher.setAdapter(new ImageAdapter(this));
        imageSwitcher.setOnItemSelectedListener(this);
        btnStart = (Button) findViewById(R.id.start_button);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboradingActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivFist = (ImageView) findViewById(R.id.onbording_first);
        ivSecond = (ImageView) findViewById(R.id.onbording_second);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                ivFist.setImageResource(R.mipmap.onboarding_select);
                ivSecond.setImageResource(R.mipmap.onboarding_unselect);
                break;
            case 1:
                ivSecond.setImageResource(R.mipmap.onboarding_select);
                ivFist.setImageResource(R.mipmap.onboarding_unselect);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

//            i.setImageResource(mThumbIds[position]);
            i.setBackgroundResource(mImageIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//            i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }

        private Context mContext;

    }

    private Integer[] mImageIds = { R.mipmap.onborading, R.mipmap.onborading };

}
