package com.example.pr11_deepsea.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr11_deepsea.Model.TestQuestionsScreenModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.HomeTestRecyclerSampleBinding;

import java.util.ArrayList;

public class TestQuestionsScreenAdapter extends RecyclerView.Adapter<TestQuestionsScreenAdapter.viewHolder> {

    ArrayList<TestQuestionsScreenModel> testQuestionsScreenModelArrayList;
    Context context;

    public TestQuestionsScreenAdapter(ArrayList<TestQuestionsScreenModel> testQuestionsScreenModelArrayList, Context context) {
        this.testQuestionsScreenModelArrayList = testQuestionsScreenModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_test_recycler_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TestQuestionsScreenModel testQuestionsScreenModel = testQuestionsScreenModelArrayList.get(position);
        holder.binding.testSampleTitle1.setText(testQuestionsScreenModel.getTestTitle());
        holder.binding.testSampleQuestion1.setText(testQuestionsScreenModel.getTestQuestion1());
        holder.binding.testSampleQuestion2.setText(testQuestionsScreenModel.getTestQuestion2());
        holder.binding.testSampleQuestion3.setText(testQuestionsScreenModel.getTestQuestion3());
        holder.binding.testSampleQuestion4.setText(testQuestionsScreenModel.getTestQuestion4());
        holder.binding.testSampleQuestion5.setText(testQuestionsScreenModel.getTestQuestion5());
        holder.binding.testSampleQuestion6.setText(testQuestionsScreenModel.getTestQuestion6());
        holder.binding.testSampleQuestion7.setText(testQuestionsScreenModel.getTestQuestion7());

        holder.binding.testSampleSubmitButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int answerTotal = 0;

                if (holder.binding.radioButton1Que1.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que1.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que1.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que1.isChecked()) {
                    answerTotal = answerTotal+4;
                }

                if (holder.binding.radioButton1Que2.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que2.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que2.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que2.isChecked()) {
                    answerTotal = answerTotal+4;
                }

                if (holder.binding.radioButton1Que3.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que3.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que3.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que3.isChecked()) {
                    answerTotal = answerTotal+4;
                }

                if (holder.binding.radioButton1Que4.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que4.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que4.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que4.isChecked()) {
                    answerTotal = answerTotal+4;
                }

                if (holder.binding.radioButton1Que5.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que5.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que5.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que5.isChecked()) {
                    answerTotal = answerTotal+4;
                }

                if (holder.binding.radioButton1Que6.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que6.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que6.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que6.isChecked()) {
                    answerTotal = answerTotal+4;
                }

                if (holder.binding.radioButton1Que7.isChecked()){
                    answerTotal++;
                } else if (holder.binding.radioButton2Que7.isChecked()) {
                    answerTotal = answerTotal+2;
                }else if (holder.binding.radioButton3Que7.isChecked()) {
                    answerTotal = answerTotal+3;
                }else if (holder.binding.radioButton4Que7.isChecked()) {
                    answerTotal = answerTotal+4;
                }




                Toast.makeText(context,String.valueOf(answerTotal), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return testQuestionsScreenModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        HomeTestRecyclerSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeTestRecyclerSampleBinding.bind(itemView);
        }
    }
}
