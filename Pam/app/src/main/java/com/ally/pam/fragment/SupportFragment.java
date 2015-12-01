package com.ally.pam.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ally.pam.R;

/**
 * Created by Ally on 11/25/2015.
 */
public class SupportFragment extends Fragment {

    public SupportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_support, container, false);
        return rootView;
    }
}