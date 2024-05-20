package com.example.pr11_deepsea.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr11_deepsea.Adapter.ProfileTestResultAdapter;
import com.example.pr11_deepsea.Model.ProfileTestResultModel;
import com.example.pr11_deepsea.Model.UserModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentProfileBinding;
import com.example.pr11_deepsea.ui.Features.EditProfileActivity;
import com.example.pr11_deepsea.ui.Features.HomeTestResultActivity;
import com.example.pr11_deepsea.ui.SignInPages.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container,false);
        View view = binding.getRoot();


        // background change as the UI configuration changes

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:

                break;
            case Configuration.UI_MODE_NIGHT_NO:
                binding.profileScrollView0.setBackgroundResource(R.drawable.app_background1);
                break;
        }


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){
                            UserModel user = snapshot.getValue(UserModel.class);
                            Picasso.get()
                                    .load(user.getProfilePhoto())
                                    .placeholder(R.drawable.baseline_person_24)
                                    .into(binding.ProfileImage1);

                            binding.ProfileName0.setText(user.getName());
                            binding.profileContactEmail1.setText(user.getEmail());
                            binding.profileContactNumber1.setText(user.getPhoneNo());
                            binding.profileLocation1.setText(user.getLocation());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        //Profile Test Result Recycler
        ArrayList<ProfileTestResultModel> testResultModelArrayList = new ArrayList<>();

       // testResultModelArrayList.add(new ProfileTestResultModel(4646,"jioegiv","ejiogje",56));


        ProfileTestResultAdapter testResultAdapter = new ProfileTestResultAdapter(getContext(),testResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.profileTestResultRecycler1.setAdapter(testResultAdapter);
        binding.profileTestResultRecycler1.setLayoutManager(linearLayoutManager);

        database.getReference().child("Users").child(auth.getUid())
                .child("Tests").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        testResultModelArrayList.clear();

                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            ProfileTestResultModel testResultModel = dataSnapshot.getValue(ProfileTestResultModel.class);
//                            if (testResultModel!=null){
//                                testResultModel.setTestId(snapshot.getKey());
//                            }

                            testResultModelArrayList.add(testResultModel);

                        }
                        testResultAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });








        binding.profileEditButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });



        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        return view;
    }
}