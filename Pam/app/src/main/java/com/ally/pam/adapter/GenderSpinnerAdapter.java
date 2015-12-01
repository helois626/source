package com.ally.pam.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ally.pam.R;

import java.util.ArrayList;

/**
 * Created by Ally on 11/27/2015.
 */
public class GenderSpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> data;
    String tempValues = null;
    Typeface typeface;
    LayoutInflater inflater;

    public GenderSpinnerAdapter(Context activitySpinner, int textViewResourceId,
                          ArrayList<String> objects) {
        super(activitySpinner, textViewResourceId, objects);
        context = activitySpinner;
        data = objects;
//        typeface = Typeface.createFromAsset(context.getAssets(),
//                "LATO-REGULAR_0.TTF");
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.dropdown_row, parent, false);
        tempValues = null;
        tempValues = data.get(position);
        TextView label = (TextView) row.findViewById(R.id.spinner_row);
//        label.setTypeface(typeface);
        label.setText(tempValues);
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.gender_spinner_item, parent, false);
        tempValues = null;
        tempValues = data.get(position);
        TextView label = (TextView) row.findViewById(R.id.gender_textview);
//        label.setTypeface(typeface);
        label.setText(tempValues);
        return row;
    }
}
