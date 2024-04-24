package com.example.pr11_deepsea.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr11_deepsea.Model.TestHomeScreen1Model;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.TestScreen1Recycler1Binding;
import com.example.pr11_deepsea.ui.Features.TestQuestionsPage;

import java.util.ArrayList;

public class TestHomeScreen1Adapter extends RecyclerView.Adapter<TestHomeScreen1Adapter.viewHolder>{

    ArrayList<TestHomeScreen1Model> testHomeScreen1ModelArrayList;
    Context context;

    public TestHomeScreen1Adapter(ArrayList<TestHomeScreen1Model> testHomeScreen1ModelArrayList, Context context) {
        this.testHomeScreen1ModelArrayList = testHomeScreen1ModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_screen1_recycler1, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TestHomeScreen1Model testHomeScreen1Model = testHomeScreen1ModelArrayList.get(position);


        holder.binding.testScreenTestTitle1.setText(testHomeScreen1Model.getTestScreen1Title1());
        holder.binding.testScreen1TestImage1.setImageResource(testHomeScreen1Model.getTestScreen1Image1());

        holder.binding.testScreenTestCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.getContext().startActivity(new Intent(context, TestQuestionsPage.class));
                String value = testHomeScreen1Model.getTestScreen1Title1();
                Intent intent = new Intent(context, TestQuestionsPage.class);
                intent.putExtra("TitleOfTest", value);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return testHomeScreen1ModelArrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        TestScreen1Recycler1Binding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TestScreen1Recycler1Binding.bind(itemView);
        }
    }
}
