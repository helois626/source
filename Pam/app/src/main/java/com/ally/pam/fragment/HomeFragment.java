package com.ally.pam.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ally.pam.activities.HomeActivity;
import com.ally.pam.activities.NewRequestActivity;
import com.ally.pam.R;

/**
 * Created by Ally on 11/25/2015.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ImageButton ibtnNotification;
    private ImageButton ibtnHelp;
    private ImageButton ibtnRequest;
    private Context context;

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ibtnNotification = (ImageButton) rootView.findViewById(R.id.ibtn_notification);
        ibtnHelp = (ImageButton) rootView.findViewById(R.id.ibtn_help);
        ibtnRequest = (ImageButton) rootView.findViewById(R.id.ibtn_request);
        ibtnNotification.setOnClickListener(this);
        ibtnHelp.setOnClickListener(this);
        ibtnRequest.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        HomeActivity homeActivity = (HomeActivity)context;
        int id = v.getId();
        switch (id) {
            case R.id.ibtn_notification:
                homeActivity.displayView(3);
                break;
            case R.id.ibtn_help:
                Intent i = new Intent(homeActivity, NewRequestActivity.class);
                startActivity(i);
                break;
            case R.id.ibtn_request:
                homeActivity.displayView(2);
                break;
        }
    }
}
