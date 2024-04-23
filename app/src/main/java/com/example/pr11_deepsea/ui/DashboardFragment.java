package com.example.pr11_deepsea.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pr11_deepsea.Adapter.DashboardAdapter;
import com.example.pr11_deepsea.Model.DashboardModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<DashboardModel> dashboardModelArrayList = new ArrayList<>();
        dashboardModelArrayList.add(new DashboardModel(R.drawable.yoga,"Exercise",getString(R.string.extercise)));
        dashboardModelArrayList.add(new DashboardModel(R.drawable.meditation,"Meditation and Mindfulness",getString(R.string.meditation)));
        dashboardModelArrayList.add(new DashboardModel(R.drawable.nature,"Spending time in nature",getString(R.string.naturetime)));
        dashboardModelArrayList.add(new DashboardModel(R.drawable.creative_process,"Creative outlets",getString(R.string.creativity)));
        dashboardModelArrayList.add(new DashboardModel(R.drawable.social,"Socializing",getString(R.string.socializing0)));
        dashboardModelArrayList.add(new DashboardModel(R.drawable.healthyfood1,"Healthy eating",getString(R.string.eating)));
        dashboardModelArrayList.add(new DashboardModel(R.drawable.sleep,"Quality sleep",getString(R.string.sleep)));

        DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboardModelArrayList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.dashboardRecyclerview1.setAdapter(dashboardAdapter);
        binding.dashboardRecyclerview1.setLayoutManager(layoutManager);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}