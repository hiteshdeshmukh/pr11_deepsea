package com.example.pr11_deepsea.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr11_deepsea.Model.CommentModel;
import com.example.pr11_deepsea.Model.PostModel;
import com.example.pr11_deepsea.Model.UserModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.HomeSecrecyPostSampleBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder>{

    ArrayList<PostModel> postModelArrayList;
    Context context;

    public PostAdapter(ArrayList<PostModel> postModelArrayList, Context context) {
        this.postModelArrayList = postModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_secrecy_post_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostModel postModel = postModelArrayList.get(position);

        String postImage = postModel.getPostImage();
        if (postImage == null){
            holder.binding.PostImage1.setVisibility(View.GONE);
        }else {
            Picasso.get()
                    .load(postModel.getPostImage())
                    .placeholder(R.drawable.baseline_person_outline_24)
                    .into(holder.binding.PostImage1);
        }




        holder.binding.postTime1.setText(postModel.getPostedAt()+"");
       // String postTime = TimeAgo.using(postModel.getPostedAt());
      //  holder.binding.postTime1.setText(postTime);

        String description = postModel.getPostDescription();
        if (description.equals("")){
            holder.binding.homePostCaption0.setVisibility(View.GONE);
        }else {
            holder.binding.homePostCaption0.setText(postModel.getPostDescription());
            holder.binding.homePostCaption0.setVisibility(View.VISIBLE);
        }


        holder.binding.homePostComment0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_comment);

                RecyclerView commentRecycler = dialog.findViewById(R.id.commentRecycler1);
                EditText commentEditText = dialog.findViewById(R.id.commentText1);
                ImageButton commentSendButton = dialog.findViewById(R.id.commentSendButton1);

                ArrayList<CommentModel> commentModelArrayList = new ArrayList<>();



                CommentAdapter commentAdapter = new CommentAdapter(context,commentModelArrayList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                commentRecycler.setLayoutManager(layoutManager);
                commentRecycler.setAdapter(commentAdapter);

                FirebaseDatabase.getInstance().getReference().child("posts").child(postModel.getPostId())
                        .child("comments").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                commentModelArrayList.clear();
                                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                    CommentModel commentModel = dataSnapshot.getValue(CommentModel.class);
                                    commentModelArrayList.add(commentModel);
                                }
                                commentAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                commentSendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CommentModel commentModel = new CommentModel();
                        commentModel.setCommentBody(commentEditText.getText().toString());
                        commentModel.setCommentedAt(new Date().getTime());
                        commentModel.setCommentedBy(FirebaseAuth.getInstance().getUid());

                        FirebaseDatabase.getInstance().getReference().child("posts").child(postModel.getPostId())
                                .child("comments").push().setValue(commentModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        FirebaseDatabase.getInstance().getReference().child("posts")
                                                .child(postModel.getPostId()).child("commentCount")
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        int commentCount = 0;
                                                        if(snapshot.exists()){
                                                            commentCount = snapshot.getValue(Integer.class);
                                                        }
                                                        FirebaseDatabase.getInstance().getReference().child("posts")
                                                                .child(postModel.getPostId()).child("commentCount")
                                                                .setValue(commentCount+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        commentEditText.setText("");
                                                                        Toast.makeText(dialog.getContext(), "Commented successfully", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                    }
                                });
                    }
                });


                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation1;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

            }
        });

        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(postModel.getPostedBy()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel user = snapshot.getValue(UserModel.class);
                        Picasso.get()
                                .load(user.getProfilePhoto())
                                .placeholder(R.drawable.baseline_person_24)
                                .into(holder.binding.postProfileImage1);
                        holder.binding.postUsername1.setText(user.getName());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance().getReference().child("posts").child(postModel.getPostId())
                .child("likes").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            holder.binding.homePostLike0.setImageResource(R.drawable.baseline_favorite_24);
                        }else {
                            holder.binding.homePostLike0.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("posts").child(postModel.getPostId())
                                            .child("likes")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference()
                                                            .child("posts").child(postModel.getPostId())
                                                            .child("postLike")
                                                            .setValue(postModel.getPostLike() + 1 ).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    holder.binding.homePostLike0.setImageResource(R.drawable.baseline_favorite_24);
                                                                }
                                                            });
                                                }
                                            });
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        HomeSecrecyPostSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeSecrecyPostSampleBinding.bind(itemView);
        }
    }
}
