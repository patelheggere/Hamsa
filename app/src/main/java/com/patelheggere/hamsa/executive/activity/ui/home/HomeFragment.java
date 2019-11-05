package com.patelheggere.hamsa.executive.activity.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.patelheggere.hamsa.executive.R;
import com.patelheggere.hamsa.executive.activity.CustomerDetailsActivity;
import com.patelheggere.hamsa.executive.activity.LoginActivity;
import com.patelheggere.hamsa.executive.adapter.TaskAssignedAdapter;
import com.patelheggere.hamsa.executive.model.AssignedTasksModel;
import com.patelheggere.hamsa.executive.network.ApiInterface;
import com.patelheggere.hamsa.executive.network.RetrofitInstance;
import com.patelheggere.hamsa.executive.utils.RecyclerItemClickListener;
import com.patelheggere.hamsa.executive.utils.SharedPrefsHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    private ApiInterface apiInterface;
    private RecyclerView mRecyclerViewTasks;
    private TaskAssignedAdapter mTaskAssignedAdapter;
    private List<AssignedTasksModel> mAssignedTasksModelList;
    private ProgressBar mProgressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        setUpNetwork();
        mRecyclerViewTasks = root.findViewById(R.id.recyclerViewTasks);
        mProgressBar = root.findViewById(R.id.progress_circular);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        mRecyclerViewTasks.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerViewTasks ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), CustomerDetailsActivity.class);
                        intent.putExtra("DATA", mAssignedTasksModelList.get(position));
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        reportPageViewToFirebaseAnalytics(getActivity(), "Home Fragment");
        initData();
        return root;
    }

    private void initData(){
        String id = SharedPrefsHelper.getInstance().get("ID", null);
        if(id!=null)
        {
            mProgressBar.setVisibility(View.VISIBLE);
            Call<List<AssignedTasksModel>> assignedTasksModelCall = apiInterface.getTaskAssignedToExe(id);
            assignedTasksModelCall.enqueue(new Callback<List<AssignedTasksModel>>()
            {
                @Override
                public void onResponse(Call<List<AssignedTasksModel>> call, Response<List<AssignedTasksModel>> response) {
                   mProgressBar.setVisibility(View.GONE);
                   if(response.body()!=null && response.body().size()!=0) {
                       mAssignedTasksModelList = response.body();
                       mTaskAssignedAdapter = new TaskAssignedAdapter(getContext(), response.body());
                       mRecyclerViewTasks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                       mRecyclerViewTasks.setAdapter(mTaskAssignedAdapter);
                   }
                   else {
                       Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
                   }
                }

                @Override
                public void onFailure(Call<List<AssignedTasksModel>> call, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
                }
            });
        }
        Call<Object> objectCall = apiInterface.getData("oustqa");
        objectCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse: "+response.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }
    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }

    private FirebaseAnalytics mFirebaseAnalytics;


    public void reportPageViewToFirebaseAnalytics(Activity activity, String screenName) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        // [START set_current_screen]
        /*if (com.oustme.oustsdk.tools.OustPreferences.get("tanentId") != null) {
            screenName = com.oustme.oustsdk.tools.OustPreferences.get("tanentId") + "_" + screenName;
        }*/
        mFirebaseAnalytics.setCurrentScreen(activity, screenName, null /* class override */);

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "screen");
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, screenName);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }
}