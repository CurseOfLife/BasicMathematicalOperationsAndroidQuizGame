package com.example.projectmv3.views.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectmv3.R;

//OLD CODE
//MIGHT GET TO DOING THIS ONE DAY
//Busy doing other things
//online mode needs an api..in the process of researching that
public class OnlineLoginFragment extends Fragment {

    private Button btn_play, btn_sign_up, btn_help;
    private NavController navController;

    public OnlineLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        btn_play = view.findViewById(R.id.btn_play);
        btn_sign_up = view.findViewById(R.id.btn_sign_up);
        btn_help = view.findViewById(R.id.btn_help);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_onlineLoginFragment_to_mainMenuFragment);
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_onlineLoginFragment_to_onlineRegisterFragment);
            }
        });


        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_onlineLoginFragment_to_authHelpFragment);
            }
        });

    }
}