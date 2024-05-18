package com.example.pr11_deepsea.ui.Features;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityHomeTestResultBinding;

public class HomeTestResultActivity extends AppCompatActivity {

    ActivityHomeTestResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeTestResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle homeResultTransfer = getIntent().getExtras();
        int homeResultNumber = homeResultTransfer.getInt("TranferTestResultIntent");

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

    }
}