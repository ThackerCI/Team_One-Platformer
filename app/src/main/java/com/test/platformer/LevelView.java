package com.test.platformer;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class LevelView extends Fragment {

    public LevelView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Environment environment = new Environment();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_view, container, false);
    }
}
