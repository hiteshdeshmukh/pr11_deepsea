package com.example.pr11_deepsea.ui.Features;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pr11_deepsea.Adapter.TestAdapter;
import com.example.pr11_deepsea.Model.TestModel;
import com.example.pr11_deepsea.databinding.ActivityTestQuestionsPageBinding;

import com.example.pr11_deepsea.R;

import java.util.ArrayList;

public class TestQuestionsPage extends AppCompatActivity {

    private ActivityTestQuestionsPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestQuestionsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<TestModel> testModelArrayList = new ArrayList<>();
        testModelArrayList.add(new TestModel("feg","rgvr","sdrfber","rsdtb","rgvr","rfbsd","drfbd","srfbsr"));

        TestAdapter testAdapter = new TestAdapter(testModelArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.testQuestionPageRecycler1.setLayoutManager(layoutManager);
        binding.testQuestionPageRecycler1.setAdapter(testAdapter);

    }
}