package com.example.pr11_deepsea.ui.Features;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pr11_deepsea.Adapter.TestQuestionsScreenAdapter;
import com.example.pr11_deepsea.Model.TestQuestionsScreenModel;
import com.example.pr11_deepsea.databinding.ActivityTestQuestionsPageBinding;

import java.util.ArrayList;
import java.util.Objects;

public class TestQuestionsPage extends AppCompatActivity {

    private ActivityTestQuestionsPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestQuestionsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<TestQuestionsScreenModel> testQuestionsScreenModelArrayList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String TitleOfTest = extras.getString("TitleOfTest");
            if (Objects.equals(TitleOfTest, "Anxiety")){
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel("feg","rgvr","sdrfber","rsdtb","rgvr","rfbsd","drfbd","srfbsr"));


            }
        }


        TestQuestionsScreenAdapter testQuestionsScreenAdapter = new TestQuestionsScreenAdapter(testQuestionsScreenModelArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.testQuestionPageRecycler1.setLayoutManager(layoutManager);
        binding.testQuestionPageRecycler1.setAdapter(testQuestionsScreenAdapter);

    }
}