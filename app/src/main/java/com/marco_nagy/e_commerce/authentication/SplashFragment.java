package com.marco_nagy.e_commerce.authentication;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.FragmentSplashBinding;

import org.jetbrains.annotations.NotNull;


public class SplashFragment extends Fragment {
    FragmentSplashBinding binding;
    NavController navController;
    Animation textAnim, layoutAnim;
    private static final String TAG = "SplashFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        textAnim = AnimationUtils.loadAnimation(getContext(), R.anim.animation);
        layoutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_to_top);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.splash.setVisibility(View.VISIBLE);
                binding.splash.setAnimation(layoutAnim);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.shop.setVisibility(View.VISIBLE);
                        binding.shop.setAnimation(textAnim);
                        binding.now.setVisibility(View.VISIBLE);
                        binding.now.setAnimation(textAnim);
                    }
                }, 500);
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             String token=  SharedPref.read(SharedPref.Token, null);
                Log.i(TAG, "run: "+ token);
                if (token== null){
                    navController.navigate(R.id.action_splashFragment_to_signUp_Fragment);
                }else{
                    navController.navigate(R.id.action_splashFragment_to_homeActivity);
                }

            }
        }, 5500);
    }
}
