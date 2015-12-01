package com.ally.pam.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ally.pam.R;
import com.ally.pam.adapter.NotificationListAdapter;

/**
 * Created by Ally on 11/25/2015.
 */
public class NotificationFragment extends Fragment {

    private ListView lvNotification;

//    public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    public static int[] image = {0, 0, 0, 0, 0};
    public static String[] TITLE = {"Test Notification","Test Notification","Test Notification","Test Notification","Test Notification"};
    public static String[] STATUS = {"3 Days ago","Failed","1 Day ago","Just Now!", ""};

    public NotificationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        lvNotification = (ListView) rootView.findViewById(R.id.notification_listView);
        lvNotification.setAdapter(new NotificationListAdapter(getActivity(), image, TITLE, STATUS));

        return rootView;
    }
}
