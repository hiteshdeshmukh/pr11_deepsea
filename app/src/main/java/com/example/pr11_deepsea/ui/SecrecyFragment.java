package com.example.pr11_deepsea.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentSecrecyBinding;

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



        return view;
    }
}