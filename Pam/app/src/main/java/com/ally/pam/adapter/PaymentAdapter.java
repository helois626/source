package com.ally.pam.adapter;

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
 * Created by Ally on 12/2/2015.
 */
public class PaymentAdapter extends BaseAdapter {

    String[] NAME;
    String[] COST;
    int[] IMAGE;
    Context context;
    private static LayoutInflater inflater = null;

    public PaymentAdapter(Context mainActivity, String[] name, int[] image, String[] cost) {
        // TODO Auto-generated constructor stub
        IMAGE = image;
        NAME = name;
        COST = cost;
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
        TextView tvName;
        ImageView imageView;
        TextView tvCost;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.validate_item, null);

        holder.tvName = (TextView) rowView.findViewById(R.id.name_textview);
        holder.imageView = (ImageView) rowView.findViewById(R.id.pay_imageview);
        holder.tvCost = (TextView) rowView.findViewById(R.id.cost_imageview);

        holder.tvName.setText(NAME[position]);
        holder.tvCost.setText(COST[position]);
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
