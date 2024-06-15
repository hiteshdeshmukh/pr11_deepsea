package com.example.pr11_deepsea.ui.SignInPages;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pr11_deepsea.MainActivity;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivitySplashScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    FirebaseAuth auth;

    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // background change as the UI configuration changes

//        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
//            case Configuration.UI_MODE_NIGHT_YES:
//                //binding.splashScreenLinearLayout1.setBackgroundResource(R.drawable.app_background1);
//                //binding.splashScreenLinearLayout1.setBackgroundColor(Color.WHITE);
//                binding.splashScreenLinearLayout1.setBackgroundResource(R.drawable.logo11111);
//                break;
//            case Configuration.UI_MODE_NIGHT_NO:
//                //binding.splashScreenLinearLayout1.setBackgroundResource(R.drawable.app_background1);
//                binding.splashScreenLinearLayout1.setBackgroundResource(R.drawable.logo11111);
//                //binding.splashScreenLinearLayout1.setBackgroundColor(Color.WHITE);
//                break;
//        }



        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();


//        binding.splashScreenSignInButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.splashScreenSignUpButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SplashScreenActivity.this, SignUpActivity.class);
//                startActivity(intent);
//            }
//        });

    }


    @Override
    protected void onStart(){
        super.onStart();
        if (currentUser != null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            },800);

        }else{
            Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);
            startActivity(intent);
        }
    }
}