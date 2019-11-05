package com.patelheggere.hamsa.executive.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patelheggere.hamsa.executive.R;
import com.patelheggere.hamsa.executive.model.AssignedTasksModel;

import java.util.List;

public class TaskAssignedAdapter extends RecyclerView.Adapter<TaskAssignedAdapter.TaskViewHolder>{
    private static final String TAG = "TaskAssignedAdapter";
    List<AssignedTasksModel> jobList;
    private Context mContext;

    public TaskAssignedAdapter(Context mContext, List<AssignedTasksModel> jobList) {
        this.jobList = jobList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.assigned_task_item, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
       AssignedTasksModel tasksModel = jobList.get(i);
       if(tasksModel!=null){
           taskViewHolder.name.setText(tasksModel.getName());
           taskViewHolder.phone.setText(tasksModel.getPhone());
           taskViewHolder.adds.setText(tasksModel.getAdds());
       }

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, adds, noti_date, lastDate, examMode, payment, documents, examDate, noPost, website;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            phone = itemView.findViewById(R.id.custPhone);
            adds = itemView.findViewById(R.id.custAdds);
        }
    }
}
