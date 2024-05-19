package com.example.pr11_deepsea.ui.Features;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pr11_deepsea.Model.ProfileTestResultModel;
import com.example.pr11_deepsea.Model.UserModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityHomeTestResultBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeTestResultActivity extends AppCompatActivity {

    ActivityHomeTestResultBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeTestResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle homeResultTransfer = getIntent().getExtras();
        int homeResultNumber = homeResultTransfer.getInt("TranferTestResultIntent");

        String testName = homeResultTransfer.getString("TransferTitleOfTest");

        binding.homeTestResultTestName1.setText(testName);
        binding.homeTestResultTestName2.setText(testName);

        binding.homeTestResultNumber0.setText(String.valueOf(homeResultNumber));
        binding.homeTestResultNumber1.setText(String.valueOf(homeResultNumber));

        //TestLevel
        String testLevel = "";

        if (homeResultNumber == 0) {
            Toast.makeText(HomeTestResultActivity.this, "Not Selected any Answers", Toast.LENGTH_SHORT).show();
        } else if (homeResultNumber < 5 && homeResultNumber > 0) {
            testLevel = "Minimal";
            binding.homeTestResultTableRow1.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber < 10 && homeResultNumber > 4) {
            testLevel = "Mild";
            binding.homeTestResultTableRow2.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber < 15 && homeResultNumber > 9) {
            testLevel = "Moderate";
            binding.homeTestResultTableRow3.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber < 20 && homeResultNumber > 14) {
            testLevel = "Moderately severe";
            binding.homeTestResultTableRow4.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber < 29 && homeResultNumber > 19) {
            testLevel = "Severe";
            binding.homeTestResultTableRow5.setBackground(new ColorDrawable(Color.GRAY));
        }


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        String finalTestLevel = testLevel;

        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            UserModel user = snapshot.getValue(UserModel.class);

                            //long eMilli = Long.parseLong(String.valueOf(new Date().getTime()));
                            // String postTime = TimeAgo.using(eMilli);

                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                            long milli = new Date().getTime();
                            String formattedDate = df.format(milli);

                            binding.homeTestResultDate1.setText(String.valueOf(formattedDate));

                            binding.homeTestResultAge1.setText(user.getAge());
                            binding.homeTestResultGender1.setText(user.getGender());

                            //adding test result into Firebase Database

                            ProfileTestResultModel testResultModel = new ProfileTestResultModel();
                            testResultModel.setTestDate(milli);
                            testResultModel.setTestName(testName);
                            testResultModel.setTestScore(homeResultNumber);
                            testResultModel.setTestLevel(finalTestLevel);

                            database.getReference().child("Users").child(auth.getUid())
                                    .child("Tests").push().setValue(testResultModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            Toast.makeText(HomeTestResultActivity.this,"Saved Result Data Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}