package com.example.pr11_deepsea.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr11_deepsea.Model.TestModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.HomeTestRecyclerSampleBinding;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.viewHolder> {

    ArrayList<TestModel> testModelArrayList;
    Context context;

    public TestAdapter(ArrayList<TestModel> testModelArrayList, Context context) {
        this.testModelArrayList = testModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_test_recycler_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TestModel testModel = testModelArrayList.get(position);
        holder.binding.testSampleTitle1.setText(testModel.getTestTitle());
        holder.binding.testSampleQuestion1.setText(testModel.getTestQuestion1());
        holder.binding.testSampleQuestion2.setText(testModel.getTestQuestion2());
        holder.binding.testSampleQuestion3.setText(testModel.getTestQuestion3());
        holder.binding.testSampleQuestion4.setText(testModel.getTestQuestion4());
        holder.binding.testSampleQuestion5.setText(testModel.getTestQuestion5());
        holder.binding.testSampleQuestion6.setText(testModel.getTestQuestion6());
        holder.binding.testSampleQuestion7.setText(testModel.getTestQuestion7());


    }

    @Override
    public int getItemCount() {
        return testModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        HomeTestRecyclerSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeTestRecyclerSampleBinding.bind(itemView);
        }
    }
}
