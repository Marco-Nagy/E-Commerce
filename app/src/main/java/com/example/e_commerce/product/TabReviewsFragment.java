package com.example.e_commerce.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.ReviewItem;
import com.example.e_commerce.ReviewsAdapter;
import com.example.e_commerce.databinding.FragmentTabReviewsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class TabReviewsFragment extends Fragment {

FragmentTabReviewsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_reviews, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setReviewsRecyclerView();
    }
    public void setReviewsRecyclerView() {
        List<ReviewItem> reviewItems = new ArrayList<>();
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));
        reviewItems.add(new ReviewItem(R.string.jd,R.drawable.rating_3,"Jane Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","10 Oct, 2018"));

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(reviewItems, getContext());
        binding.reviewRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.reviewRV.setAdapter(reviewsAdapter);

    }
}