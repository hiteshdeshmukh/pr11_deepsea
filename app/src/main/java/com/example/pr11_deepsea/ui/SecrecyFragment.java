package com.example.pr11_deepsea.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentSecrecyBinding;
import com.example.pr11_deepsea.ui.Features.AddPostActivity;

public class SecrecyFragment extends Fragment {

    private FragmentSecrecyBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSecrecyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.secrecyActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPostActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}