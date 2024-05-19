package com.example.pr11_deepsea.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr11_deepsea.Model.ProfileTestResultModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ProfileTestResultRecylerSampleBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProfileTestResultAdapter extends RecyclerView.Adapter<ProfileTestResultAdapter.viewHolder>{

    Context context;
    ArrayList<ProfileTestResultModel> profileTestResultModelArrayList;

    public ProfileTestResultAdapter(Context context, ArrayList<ProfileTestResultModel> profileTestResultModelArrayList) {
        this.context = context;
        this.profileTestResultModelArrayList = profileTestResultModelArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_test_result_recyler_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ProfileTestResultModel resultModel = profileTestResultModelArrayList.get(position);


        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(resultModel.getTestDate());

        holder.binding.profileTestResultDate1.setText(formattedDate+"");
        holder.binding.profileTestResultTestName1.setText(resultModel.getTestName());
        holder.binding.profileTestResultScoreNumber1.setText(resultModel.getTestScore()+"");
        holder.binding.profileTestResultLevel1.setText(resultModel.getTestLevel());

    }

    @Override
    public int getItemCount() {
        return profileTestResultModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ProfileTestResultRecylerSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ProfileTestResultRecylerSampleBinding.bind(itemView);
        }
    }
}
