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

import com.example.pr11_deepsea.Model.UserModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityHomeTestResultBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;

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

        if (homeResultNumber==0){
            Toast.makeText(HomeTestResultActivity.this, "Not Selected any Answers", Toast.LENGTH_SHORT).show();
        } else if (homeResultNumber<5 && homeResultNumber>0) {
            binding.homeTestResultTableRow1.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber<10 && homeResultNumber>4) {
            binding.homeTestResultTableRow2.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber<15 && homeResultNumber>9) {
            binding.homeTestResultTableRow3.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber<20 && homeResultNumber>14) {
            binding.homeTestResultTableRow4.setBackground(new ColorDrawable(Color.GRAY));
        } else if (homeResultNumber<29 && homeResultNumber>19) {
            binding.homeTestResultTableRow5.setBackground(new ColorDrawable(Color.GRAY));
        }


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){
                            UserModel user = snapshot.getValue(UserModel.class);

                            //long eMilli = Long.parseLong(String.valueOf(new Date().getTime()));
                           // String postTime = TimeAgo.using(eMilli);

                            binding.homeTestResultDate1.setText(String.valueOf(new Date().getTime()));
                            binding.homeTestResultAge1.setText(user.getAge());
                            binding.homeTestResultGender1.setText(user.getGender());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}