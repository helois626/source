package com.ally.pam.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.ally.pam.R;

/**
 * Created by Ally on 12/1/2015.
 */
public class ChatAdapter extends BaseAdapter {

    int[] IMAGE;
    Context context;
    private static LayoutInflater inflater = null;

    public ChatAdapter(Context mainActivity, int[] image) {
        // TODO Auto-generated constructor stub
        IMAGE = image;
        context = mainActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return IMAGE.length;
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
        ImageView imageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.imageview_item, null);

        holder.imageView = (ImageView) rowView.findViewById(R.id.item_imageview);

        holder.imageView.setImageResource(IMAGE[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + position, Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
