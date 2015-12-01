package com.ally.pam.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ally.pam.R;
import com.ally.pam.activities.RequestDetailActivity;

/**
 * Created by Ally on 11/29/2015.
 */
public class RequestListAdapter extends BaseAdapter{
    String[] TITLE;
    String[] CITY;
    String[] STATUS;
    String[] DATER;
    Context context;
    private static LayoutInflater inflater = null;

    public RequestListAdapter(Activity mainActivity, String[] title, String[] city, String[] status, String[] date) {
        // TODO Auto-generated constructor stub
        TITLE = title;
        CITY = city;
        STATUS = status;
        DATER = date;
        context = mainActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TITLE.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tvTitle;
        TextView tvCity;
        TextView tvStatus;
        TextView ivDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.request_item, null);

        holder.tvTitle = (TextView) rowView.findViewById(R.id.title_textview);
        holder.tvCity = (TextView) rowView.findViewById(R.id.city_textview);
        holder.tvStatus = (TextView) rowView.findViewById(R.id.status_textview);
        holder.ivDate = (TextView) rowView.findViewById(R.id.date_textview);

        holder.tvTitle.setText(TITLE[position]);
        holder.tvCity.setText(CITY[position]);
        holder.tvStatus.setText(STATUS[position]);
        holder.ivDate.setText(DATER[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(context, RequestDetailActivity.class);
                context.startActivity(i);
//                Toast.makeText(context, "You Clicked " + TITLE[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
