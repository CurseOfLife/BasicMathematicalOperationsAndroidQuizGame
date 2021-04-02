package com.example.projectmv3.views.achievements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmv3.R;

//CURRENTLY EMPTY AS IT WAS AN OLD IDEA
//NEVER GOT AROUND TO DOING IT
public class AchievementsFragment extends Fragment {

    public AchievementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievements, container, false);
    }
}