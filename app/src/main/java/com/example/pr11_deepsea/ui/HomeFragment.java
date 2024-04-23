package com.example.pr11_deepsea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pr11_deepsea.Adapter.TestHomeScreen1Adapter;
import com.example.pr11_deepsea.Model.TestHomeScreen1Model;
import com.example.pr11_deepsea.Model.TestModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentHomeBinding;
import com.example.pr11_deepsea.databinding.HomeTestRecyclerSampleBinding;
import com.example.pr11_deepsea.ui.Features.TestQuestionsPage;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<TestHomeScreen1Model> testHomeScreen1ModelArrayList = new ArrayList<>();
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_anxiety, "Anxiety"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_depression, "Depression"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_personality, "Personality Disorder"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_suicide, "Suicidality"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_eatingdisorder, "Eating Disorder"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_dissociation, "Dissociation"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_adhd, "ADHD"));
        testHomeScreen1ModelArrayList.add(new TestHomeScreen1Model(R.drawable.test_sleep, "Sleep"));

        TestHomeScreen1Adapter testHomeScreen1Adapter = new TestHomeScreen1Adapter(testHomeScreen1ModelArrayList,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        binding.testScreen1Recycler1.setAdapter(testHomeScreen1Adapter);
        binding.testScreen1Recycler1.setLayoutManager(gridLayoutManager);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}