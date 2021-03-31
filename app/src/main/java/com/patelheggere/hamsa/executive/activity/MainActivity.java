package com.patelheggere.hamsa.executive.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patelheggere.hamsa.executive.R;
import com.patelheggere.hamsa.executive.adapter.TaskAssignedAdapter;
import com.patelheggere.hamsa.executive.base.BaseActivity;
import com.patelheggere.hamsa.executive.model.AssignedTasksModel;
import com.patelheggere.hamsa.executive.network.ApiInterface;
import com.patelheggere.hamsa.executive.network.RetrofitInstance;
import com.patelheggere.hamsa.executive.utils.SharedPrefsHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private ApiInterface apiInterface;
    private RecyclerView mRecyclerViewTasks;
    private TaskAssignedAdapter mTaskAssignedAdapter;
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mRecyclerViewTasks = findViewById(R.id.recyclerViewTasks);
    }

    @Override
    protected void initData() {
        setUpNetwork();
        String id = SharedPrefsHelper.getInstance().get("ID", null);
        if(id!=null) {
            String type = SharedPrefsHelper.getInstance().get("TYPE");
            Call<List<AssignedTasksModel>> assignedTasksModelCall = apiInterface.getTaskAssignedToExe(id, type);
            assignedTasksModelCall.enqueue(new Callback<List<AssignedTasksModel>>() {
                @Override
                public void onResponse(Call<List<AssignedTasksModel>> call, Response<List<AssignedTasksModel>> response) {
                    mTaskAssignedAdapter = new TaskAssignedAdapter(MainActivity.this, response.body());
                    mRecyclerViewTasks.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                    mRecyclerViewTasks.setAdapter(mTaskAssignedAdapter);
                }

                @Override
                public void onFailure(Call<List<AssignedTasksModel>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected void initListener() {

    }

    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }
}
