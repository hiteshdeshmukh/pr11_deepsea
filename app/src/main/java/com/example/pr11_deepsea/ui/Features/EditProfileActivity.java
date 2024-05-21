package com.example.pr11_deepsea.ui.Features;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pr11_deepsea.Model.UserModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityEditProfileBinding;
import com.example.pr11_deepsea.ui.ProfileFragment;
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

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        binding.editProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });


        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);
                            Picasso.get()
                                    .load(userModel.getProfilePhoto())
                                    .placeholder(R.drawable.baseline_person_24)
                                    .into(binding.ProfileImage1);
                            binding.editProfilename.setText(userModel.getName());
                            binding.editProfileAge.setText(userModel.getAge());
                            binding.editProfileGender.setText(userModel.getGender());
                            binding.editProfileNumber1.setText(userModel.getPhoneNo());
                            binding.editProfileLocation.setText(userModel.getLocation());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        binding.editProfileImageRemove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Confirm")
                        .setMessage("Confirm remove the picture")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getApplicationContext(),"Deleting",Toast.LENGTH_SHORT).show();

                                database.getReference().child("Users").child(auth.getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                if (snapshot.exists()) {
                                                    UserModel userModel = snapshot.getValue(UserModel.class);

                                                    if (userModel.getProfilePhoto() != null){
                                                        FirebaseStorage.getInstance().getReferenceFromUrl(userModel.getProfilePhoto())
                                                                .delete();
                                                        database.getReference().child("Users").child(auth.getUid())
                                                                                .child("ProfilePhoto").removeValue();
                                                        Toast.makeText(getApplicationContext(),"Profile Photo Deleted (Refresh)",Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }

                                                }else {
                                                    Toast.makeText(getApplicationContext(), "Image Not Available", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });


        binding.editProfileConfirmChangeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel = new UserModel();
                userModel.setName(binding.editProfilename.getText().toString());
                userModel.setAge(binding.editProfileAge.getText().toString());
                userModel.setGender(binding.editProfileGender.getText().toString());
                userModel.setPhoneNo(binding.editProfileNumber1.getText().toString());
                userModel.setLocation(binding.editProfileLocation.getText().toString());

                database.getReference().child("Users").child(auth.getUid())
                        .child("name").setValue(userModel.getName());
                database.getReference().child("Users").child(auth.getUid())
                        .child("age").setValue(userModel.getAge());
                database.getReference().child("Users").child(auth.getUid())
                        .child("gender").setValue(userModel.getGender());
                database.getReference().child("Users").child(auth.getUid())
                        .child("phoneNo").setValue(userModel.getPhoneNo());
                database.getReference().child("Users").child(auth.getUid())
                        .child("location").setValue(userModel.getLocation());

                Toast.makeText(EditProfileActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditProfileActivity.this, ProfileFragment.class);

                startActivity(intent);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null) {
            Uri uri = data.getData();
            binding.ProfileImage1.setImageURI(uri);

            final StorageReference reference = storage.getReference()
                    .child("Profile_Photos")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Profile Photo Saved", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(auth.getUid()).child("ProfilePhoto").setValue(uri.toString());
                        }
                    });
                }
            });
        }
    }

}