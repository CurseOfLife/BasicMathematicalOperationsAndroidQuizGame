package com.example.projectmv3.views.game;

import android.animation.ObjectAnimator;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.projectmv3.R;

//one day ima come back to it when i research APIs
public class GameModeMenuFragment extends Fragment {

    private NavController navController;

    private ImageButton arrow_one, arrow_two, arrow_three, arrow_four;
    private Button play_gm1, play_gm2, play_gm3, play_gm4;
    private LinearLayout pDescOne, pDescTwo, pDescThree, pDescFour;

    public int rotationAngle = 0;

    public GameModeMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_mode_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrow_one = view.findViewById(R.id.arrow_one);
        arrow_two = view.findViewById(R.id.arrow_two);
        arrow_three = view.findViewById(R.id.arrow_three);
        arrow_four = view.findViewById(R.id.arrow_four);
        play_gm1 = view.findViewById(R.id.play_game_mode_one);
        play_gm2 = view.findViewById(R.id.play_game_mode_two);
        play_gm3 = view.findViewById(R.id.play_game_mode_three);
        play_gm4 = view.findViewById(R.id.play_game_mode_four);
        pDescOne = view.findViewById(R.id.parent_description_one);
        pDescTwo = view.findViewById(R.id.parent_description_two);
        pDescThree = view.findViewById(R.id.parent_description_three);
        pDescFour = view.findViewById(R.id.parent_description_four);

        navController = Navigation.findNavController(view);

        arrow_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //rotating arrow
                ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", rotationAngle, rotationAngle + 180);
                anim.setDuration(500);
                anim.start();
                rotationAngle += 180;
                rotationAngle = rotationAngle % 360;

                if (pDescOne.getVisibility() == View.VISIBLE) {
                    pDescOne.setVisibility(View.GONE);
                } else {
                    pDescOne.setVisibility(View.VISIBLE);
                }

            }
        });

        arrow_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //rotating arrow
                ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", rotationAngle, rotationAngle + 180);
                anim.setDuration(500);
                anim.start();
                rotationAngle += 180;
                rotationAngle = rotationAngle % 360;

                if (pDescTwo.getVisibility() == View.VISIBLE) {
                    pDescTwo.setVisibility(View.GONE);
                } else {
                    pDescTwo.setVisibility(View.VISIBLE);
                }

            }
        });

        arrow_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //rotating arrow
                ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", rotationAngle, rotationAngle + 180);
                anim.setDuration(500);
                anim.start();
                rotationAngle += 180;
                rotationAngle = rotationAngle % 360;

                if (pDescThree.getVisibility() == View.VISIBLE) {
                    pDescThree.setVisibility(View.GONE);
                } else {
                    pDescThree.setVisibility(View.VISIBLE);
                }
            }
        });

        arrow_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //rotating arrow
                ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", rotationAngle, rotationAngle + 180);
                anim.setDuration(500);
                anim.start();
                rotationAngle += 180;
                rotationAngle = rotationAngle % 360;

                if (pDescFour.getVisibility() == View.VISIBLE) {
                    pDescFour.setVisibility(View.GONE);
                } else {
                    pDescFour.setVisibility(View.VISIBLE);
                }
            }
        });


        play_gm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_gameModeMenuFragment_to_gamesMenuFragment);
            }
        });

        play_gm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_gameModeMenuFragment_to_gamesMenuFragment);
            }
        });


        play_gm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_gameModeMenuFragment_to_gamesMenuFragment);
            }
        });

        play_gm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_gameModeMenuFragment_to_gamesMenuFragment);
            }
        });


    }
}