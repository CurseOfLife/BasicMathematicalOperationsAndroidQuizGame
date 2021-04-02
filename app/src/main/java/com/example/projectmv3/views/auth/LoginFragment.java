package com.example.projectmv3.views.auth;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectmv3.R;
import com.example.projectmv3.adapters.SliderAdapter;
import com.example.projectmv3.model.SliderItem;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//https://www.youtube.com/watch?v=zILw5eV9QBQ
public class LoginFragment extends Fragment {

    private NavController navController;
    private MaterialCardView btn_playOnline, btn_practice;
    private ViewPager2 viewPager2;
    private List<SliderItem> sliderItems;
    private Handler sliderHandler = new Handler();
    private TextView txt_wifi; // FIX this not wifi anymore ..fix needed

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        viewPager2 = view.findViewById(R.id.vp_image_slider);
        btn_playOnline = view.findViewById(R.id.card_playOnline);
        btn_practice = view.findViewById(R.id.card_practice);
        txt_wifi = view.findViewById(R.id.txt_wifi);

        // SLIDER
        sliderItems = new ArrayList<>();
        createImageSlider();

        btn_playOnline.setOnClickListener(v -> navController.navigate(R.id.action_loginFragment_to_onlineLoginFragment));
        btn_practice.setOnClickListener(v -> navController.navigate(R.id.action_loginFragment_to_practicePlayFragment));
    }


    //creating the image slider view pager
    private void createImageSlider() {
        sliderItems.add(new SliderItem(R.drawable.plus2));
        sliderItems.add(new SliderItem(R.drawable.minus2));
        sliderItems.add(new SliderItem(R.drawable.multiplication2));
        sliderItems.add(new SliderItem(R.drawable.division2));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r = 1 - Math.abs(position);
                page.setScaleY(0.87f + r * 0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (viewPager2.getCurrentItem() == 0) {
                    sliderHandler.removeCallbacks(sliderRunnable);
                    sliderHandler.postDelayed(sliderRunnable, 1000);
                } else {
                    sliderHandler.removeCallbacks(sliderRunnable);
                    sliderHandler.postDelayed(sliderRunnable, 2500);
                }
            }
        });
    }

    //tuning view pager animations
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            float pxToDrag = viewPager2.getWidth() * 0.6f;
            ValueAnimator animator = ValueAnimator.ofFloat(0, pxToDrag);

            final float[] previousValue = {0};

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currentValue = (float) animation.getAnimatedValue();
                    float currentPxToDrag = (float) (currentValue - previousValue[0]);
                    viewPager2.fakeDragBy(-currentPxToDrag);
                    previousValue[0] = currentValue;
                }
            });

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    viewPager2.beginFakeDrag();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    viewPager2.endFakeDrag();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    //ignored
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    //ignored
                }
            });

            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(1500);
            animator.start();
        }
    };


    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }
}