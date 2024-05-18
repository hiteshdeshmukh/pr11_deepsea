package com.example.pr11_deepsea.ui.Features;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pr11_deepsea.Adapter.TestQuestionsScreenAdapter;
import com.example.pr11_deepsea.Model.TestQuestionsScreenModel;
import com.example.pr11_deepsea.R;
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
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.AnxietyQuestionTitle),getString(R.string.AnxietyQuestion1),
                        getString(R.string.AnxietyQuestion2),getString(R.string.AnxietyQuestion3),
                        getString(R.string.AnxietyQuestion4),getString(R.string.AnxietyQuestion5),
                        getString(R.string.AnxietyQuestion6),getString(R.string.AnxietyQuestion7)));
            } else if (Objects.equals(TitleOfTest, "Depression")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.DepressionTestTitle),getString(R.string.DepressionQuestion1),
                        getString(R.string.DepressionQuestion2),getString(R.string.DepressionQuestion3),
                        getString(R.string.DepressionQuestion4),getString(R.string.DepressionQuestion5),
                        getString(R.string.DepressionQuestion6),getString(R.string.DepressionQuestion7)));
            } else if (Objects.equals(TitleOfTest, "Personality Disorder")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.PersonalityDisorderTestTitle),getString(R.string.PersonalityQuestion1),
                        getString(R.string.PersonalityQuestion2),getString(R.string.PersonalityQuestion3),
                        getString(R.string.PersonalityQuestion4),getString(R.string.PersonalityQuestion5),
                        getString(R.string.PersonalityQuestion6),getString(R.string.PersonalityQuestion7)));
            } else if (Objects.equals(TitleOfTest, "Suicidality")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.SucidalityTestTitle),getString(R.string.SucidalityQuestion1),
                        getString(R.string.SucidalityQuestion2),getString(R.string.SucidalityQuestion3),
                        getString(R.string.SucidalityQuestion4),getString(R.string.SucidalityQuestion5),
                        getString(R.string.SucidalityQuestion6),getString(R.string.SucidalityQuestion7)));
            } else if (Objects.equals(TitleOfTest, "Eating Disorder")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.EatingDisorderTestTitle),getString(R.string.EatingQuestion1),
                        getString(R.string.EatingQuestion2),getString(R.string.EatingQuestion3),
                        getString(R.string.EatingQuestion4),getString(R.string.EatingQuestion5),
                        getString(R.string.EatingQuestion6),getString(R.string.EatingQuestion7)));
            } else if (Objects.equals(TitleOfTest, "Dissociation")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.DissociationTestTitle),getString(R.string.DissociationQuestion1),
                        getString(R.string.DissociationQuestion2),getString(R.string.DissociationQuestion3),
                        getString(R.string.DissociationQuestion4),getString(R.string.DissociationQuestion5),
                        getString(R.string.DissociationQuestion6),getString(R.string.DissociationQuestion7)));
            } else if (Objects.equals(TitleOfTest, "ADHD")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.ADHDTestTitle),getString(R.string.ADHDQuestion1),
                        getString(R.string.ADHDQuestion2),getString(R.string.ADHDQuestion3),
                        getString(R.string.ADHDQuestion4),getString(R.string.ADHDQuestion5),
                        getString(R.string.ADHDQuestion6),getString(R.string.ADHDQuestion7)));
            } else if (Objects.equals(TitleOfTest, "Sleep")) {
                testQuestionsScreenModelArrayList.add(new TestQuestionsScreenModel(
                        getString(R.string.SleepTitle),getString(R.string.SleepQuestion1),
                        getString(R.string.SleepQuestion1),getString(R.string.SleepQuestion1),
                        getString(R.string.SleepQuestion1),getString(R.string.SleepQuestion1),
                        getString(R.string.SleepQuestion1),getString(R.string.SleepQuestion1)));
            }

        }


        TestQuestionsScreenAdapter testQuestionsScreenAdapter = new TestQuestionsScreenAdapter(testQuestionsScreenModelArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.testQuestionPageRecycler1.setLayoutManager(layoutManager);
        binding.testQuestionPageRecycler1.setAdapter(testQuestionsScreenAdapter);

    }
}