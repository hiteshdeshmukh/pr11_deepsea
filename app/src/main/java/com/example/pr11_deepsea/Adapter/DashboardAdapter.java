package com.example.pr11_deepsea.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr11_deepsea.Model.DashboardModel;
import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityDashboardSchedulerBinding;
import com.example.pr11_deepsea.databinding.DashboardRvSampleBinding;
import com.example.pr11_deepsea.ui.Features.DashboardSchedulerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.viewHolder> {

    ArrayList<DashboardModel> dashboardModelArrayList;
    Context context;

    public DashboardAdapter(ArrayList<DashboardModel> dashboardModelArrayList, Context context) {
        this.dashboardModelArrayList = dashboardModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_rv_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DashboardModel dashboardModel = dashboardModelArrayList.get(position);
        holder.binding.dashboardImage1.setImageResource(dashboardModel.getActivityPicture());
        holder.binding.dashboardHeading1.setText(dashboardModel.getActivityHeading());
        holder.binding.dashboardInfo1.setText(dashboardModel.getActivityInfo());

        holder.binding.dashboardSceduleTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DashboardSchedulerActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        DashboardRvSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DashboardRvSampleBinding.bind(itemView);
        }
    }
}
