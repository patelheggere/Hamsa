package com.patelheggere.hamsa.executive.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.patelheggere.hamsa.executive.R;
import com.patelheggere.hamsa.executive.base.BaseActivity;
import com.patelheggere.hamsa.executive.model.ExecVerifyModel;
import com.patelheggere.hamsa.executive.network.ApiInterface;
import com.patelheggere.hamsa.executive.network.RetrofitInstance;
import com.patelheggere.hamsa.executive.utils.SharedPrefsHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private Button mMaterialButtonLogin;
    private ProgressBar mProgressBar;
    private TextInputEditText mEditTextUsername,mEditTextPassword;
    //private EditText mEditTextPassword;
    private ApiInterface apiInterface;
    @Override
    protected int getContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        mMaterialButtonLogin = findViewById(R.id.loginButton);
        mEditTextUsername = findViewById(R.id.editTextUserName);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mProgressBar =findViewById(R.id.progress_circular);
    }

    @Override
    protected void initData() {
        if(!SharedPrefsHelper.getInstance().get("IS_LOGGED_IN", true)){
            startActivity(new Intent(LoginActivity.this, LandingActivity.class));
            finish();
        }
        setUpNetwork();
    }

    @Override
    protected void initListener() {
        mMaterialButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, pwd;
                if(mEditTextPassword.getText()!=null && !mEditTextPassword.getText().toString().isEmpty()){
                    pwd = mEditTextPassword.getText().toString();
                }
                else
                {
                    mEditTextPassword.setError("Please enter password");
                    return;
                }
                if(mEditTextUsername.getText()!=null && !mEditTextUsername.getText().toString().isEmpty()){
                    uname = mEditTextUsername.getText().toString();
                }else {
                    mEditTextUsername.setError("Please enter Username");
                    return;
                }
                verifyUserData(uname, pwd);

            }
        });
    }

    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }
    private void verifyUserData(String username, String pwd){
        mProgressBar.setVisibility(View.VISIBLE);
        Call<ExecVerifyModel> verifyModelCall = apiInterface.verifyUser(username, pwd);
        verifyModelCall.enqueue(new Callback<ExecVerifyModel>() {
            @Override
            public void onResponse(Call<ExecVerifyModel> call, Response<ExecVerifyModel> response) {
                mProgressBar.setVisibility(View.GONE);
                if(response.body()!=null){
                    if(response.body().isStatus()){
                        SharedPrefsHelper.getInstance().save("NAME", response.body().getName());
                        SharedPrefsHelper.getInstance().save("ID", response.body().getId());
                        SharedPrefsHelper.getInstance().save("PHONE", response.body().getPhone());
                        SharedPrefsHelper.getInstance().save("TYPE", response.body().getType());
                        SharedPrefsHelper.getInstance().save("IS_LOGGED_IN", false);
                        startActivity(new Intent(LoginActivity.this, LandingActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ExecVerifyModel> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Something wrong, please try again", Toast.LENGTH_LONG).show();

            }
        });
    }
}
