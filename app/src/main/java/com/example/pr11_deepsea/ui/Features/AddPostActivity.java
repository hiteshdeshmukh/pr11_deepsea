package com.example.pr11_deepsea.ui.Features;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityAddPostBinding;

public class AddPostActivity extends AppCompatActivity {

    private ActivityAddPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}