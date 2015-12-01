package com.ally.pam.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ally.pam.activities.NewRequestActivity;
import com.ally.pam.R;
import com.ally.pam.adapter.NotificationListAdapter;
import com.ally.pam.adapter.RequestListAdapter;

/**
 * Created by Ally on 11/25/2015.
 */
public class RequestFragment extends Fragment implements View.OnClickListener {

    private ImageButton ibtnAddNew;

    private ListView lvRequest;

    public static String[] TITLE = {"AIRPORT TRANSFER","DINER RESERVATION","FLIGHT RESERVATION","CUSTOM REQUEST"};
    public static String[] CITY = {"City : Montereal","City : Montereal","City : Montereal","City : Montereal"};
    public static String[] STATUS = {"Status : Done","Status : In Progress","Status : Done","Status : In Progress"};
    public static String[] DATER = {"Date : November 30,2015","Date : November 30,2015","Date : November 30,2015","Date : November 30,2015"};

    public RequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);

        ibtnAddNew = (ImageButton) rootView.findViewById(R.id.ibtn_add_new);
        ibtnAddNew.setOnClickListener(this);

        lvRequest = (ListView) rootView.findViewById(R.id.request_listview);
        lvRequest.setAdapter(new RequestListAdapter(getActivity(), TITLE, CITY, STATUS, DATER));

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ibtn_add_new:
                Intent i = new Intent(getActivity(), NewRequestActivity.class);
                startActivity(i);
                break;
        }
    }
}
