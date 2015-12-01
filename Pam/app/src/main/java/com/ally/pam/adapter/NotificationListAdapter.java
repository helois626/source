package com.ally.pam.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ally.pam.R;

/**
 * Created by Ally on 11/25/2015.
 */
public class NotificationListAdapter extends BaseAdapter {
    int[] uerImage;
    String[] TITLE;
    String[] STATUS;
    Context context;
    private static LayoutInflater inflater = null;

    public NotificationListAdapter(Activity mainActivity, int[] image, String[] title, String[] status) {
        // TODO Auto-generated constructor stub
        uerImage = image;
        TITLE = title;
        STATUS = status;
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
        ImageView ivUserPic;
        TextView tvTitle;
        TextView tvStatus;
        ImageView ivStatus;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.notification_list, null);

        holder.ivUserPic = (ImageView) rowView.findViewById(R.id.iv_user_pic);
        holder.tvTitle = (TextView) rowView.findViewById(R.id.notification_title);
        holder.tvStatus = (TextView) rowView.findViewById(R.id.status_textview);
        holder.ivStatus = (ImageView) rowView.findViewById(R.id.status_imageview);

        holder.ivUserPic.setImageResource(R.mipmap.user_pic);
        holder.tvTitle.setText(TITLE[position]);
        holder.tvStatus.setText(STATUS[position]);
        holder.ivStatus.setImageResource(R.mipmap.count);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + TITLE[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
