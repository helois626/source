package com.ally.pam.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ally.pam.activities.NewRequestActivity;
import com.ally.pam.R;
import com.ally.pam.adapter.ChatAdapter;
import com.ally.pam.adapter.EventAdapter;

/**
 * Created by Ally on 11/25/2015.
 */
public class EventFragment extends Fragment implements View.OnClickListener {

    private Button ibtnAddNew;

    private ListView listView;

    private Context context;

    public EventFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        ibtnAddNew = (Button) rootView.findViewById(R.id.ibtn_add_new);
        ibtnAddNew.setOnClickListener(this);

        listView = (ListView) rootView.findViewById(R.id.event_listview);
        listView.setAdapter(new EventAdapter(context));

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