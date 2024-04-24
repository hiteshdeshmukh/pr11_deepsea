package com.example.pr11_deepsea.ui.Features;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pr11_deepsea.Model.PostModel;
import com.example.pr11_deepsea.Model.UserModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityAddPostBinding;
import com.example.pr11_deepsea.ui.SecrecyFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class AddPostActivity extends AppCompatActivity {

    private ActivityAddPostBinding binding;

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    UserModel user = snapshot.getValue(UserModel.class);
                    Picasso.get()
                            .load(user.getProfilePhoto())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(binding.homeProfileImage0);
                    binding.homeProfileName0.setText(user.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.addPost1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String description = binding.addPost1.getText().toString();
                if (!description.isEmpty()){
                   // binding.postButton.setBackgroundColor(getContext().getResources().getColor(R.color.gray));
                    binding.postButton.setBackgroundColor(getColor(R.color.gray));
                    binding.postButton.setEnabled(true);
                }else {
//                    binding.postButton.setBackgroundColor(getContext().getResources().getColor(R.color.white));
                    binding.postButton.setBackgroundColor(getColor(R.color.white));
                    binding.postButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,3);
            }
        });

        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddPostActivity.this,"Post Uploading",Toast.LENGTH_SHORT).show();

                final StorageReference reference = storage.getReference().child("posts")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child(new Date().getTime()+"");

                if (uri == null){

                    PostModel postModel = new PostModel();
                    postModel.setPostedAt(String.valueOf(new Date().getTime()));
                    postModel.setPostedBy(auth.getUid());
                    postModel.setPostDescription(binding.addPost1.getText().toString());


                  //  database.getReference().child("TextPost").child(auth.getUid());

                    database.getReference().child("posts")
                            .push()
                            .setValue(postModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(AddPostActivity.this,"Posted Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddPostActivity.this, SecrecyFragment.class);
                                    startActivity(intent);
                                }
                            });


                }else {
                    reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    PostModel post = new PostModel();
                                    post.setPostImage(uri.toString());
                                    post.setPostedBy(FirebaseAuth.getInstance().getUid());
                                    post.setPostDescription(binding.addPost1.getText().toString());
                                    post.setPostedAt(String.valueOf(new Date().getTime()));

                                    database.getReference().child("posts")
                                            .push()
                                            .setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(AddPostActivity.this,"Posted Successfully",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(AddPostActivity.this, SecrecyFragment.class);
                                                    startActivity(intent);
                                                }
                                            });
                                }
                            });
                        }
                    });

                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null){
            uri = data.getData();
            binding.addImage1.setImageURI(uri);

            binding.postButton.setBackgroundColor(AddPostActivity.this.getResources().getColor(R.color.gray));
            binding.postButton.setEnabled(true);
        }
    }

}