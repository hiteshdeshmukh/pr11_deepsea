package com.example.pr11_deepsea.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr11_deepsea.Adapter.PostAdapter;
import com.example.pr11_deepsea.Model.PostModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentSecrecyBinding;
import com.example.pr11_deepsea.ui.Features.AddPostActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecrecyFragment extends Fragment {

    private FragmentSecrecyBinding binding;

    ArrayList<PostModel> postModelArrayList;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSecrecyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();




        binding.secrecyActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPostActivity.class);
                startActivity(intent);
            }
        });

        postModelArrayList = new ArrayList<>();

        PostAdapter postAdapter = new PostAdapter(postModelArrayList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.postRecycler1.setLayoutManager(layoutManager);

        binding.shimmerPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()),R.layout.layout_demo_post_shimmering);
        binding.shimmerPostRecyclerView.showShimmer();

        binding.postRecycler1.setAdapter(postAdapter);

        database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postModelArrayList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PostModel post = dataSnapshot.getValue(PostModel.class);
                    if (post != null) {
                        post.setPostId(dataSnapshot.getKey());
                    }
                    postModelArrayList.add(post);
                }
                binding.shimmerPostRecyclerView.hideShimmer();
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}