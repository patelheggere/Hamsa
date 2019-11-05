package com.patelheggere.hamsa.executive.activity;

import androidx.appcompat.app.ActionBar;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.patelheggere.hamsa.executive.R;
import com.patelheggere.hamsa.executive.base.BaseActivity;
import com.patelheggere.hamsa.executive.model.AssignedTasksModel;
import com.patelheggere.hamsa.executive.model.CSRModel;
import com.patelheggere.hamsa.executive.model.CSRResponseModel;
import com.patelheggere.hamsa.executive.model.ProductDetails;
import com.patelheggere.hamsa.executive.model.ProductModel;
import com.patelheggere.hamsa.executive.model.ProductsOnlyModel;
import com.patelheggere.hamsa.executive.network.ApiInterface;
import com.patelheggere.hamsa.executive.network.RetrofitInstance;
import com.patelheggere.hamsa.executive.utils.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetailsActivity extends BaseActivity {
    private static final String TAG = "CustomerDetailsActivity";
    private AssignedTasksModel mAssignedTasksModel;
    private ActionBar mActionBar;
    private TextView mTextViewName, mTextViewPhone, mTextViewAdds, mTextViewId, mTextViewType, mTextViewStatus, mTextViewDetails, mTextViewTotal;
    private Spinner spinnerProduct, spinnerEquipmentType, spinnerMake, spinnerModel;
    private EditText mEditTextDefects, mEditTextRemarks, mEditTextEstimation;
    private Button CSRSubmitButton;
    private RadioGroup mRadioGroupStatus;
    private CSRModel mCSRModel;
    private RadioButton mRadioButtonComplete, mRadioButtonIncomplete, mRadioButtonPending, mRadioButtonObservation, mRadioButtonSolnProvided;
    private String product, equipmentType, make, model;
    private List<ProductModel> mProductList;
    private List<ProductsOnlyModel> mOnlyProducts;
    private List<String> mMakeList;
    private List<String> mModelList;
    private List<String> mEType;

    private ApiInterface apiInterface;
    private List<String> pnames;
    private RadioButton radioStatusButton;
    private ProgressBar mProgressBar;



    @Override
    protected int getContentView() {
        return R.layout.activity_customer_details;
    }

    @Override
    protected void initView() {
        mTextViewName = findViewById(R.id.custName);
        mTextViewAdds = findViewById(R.id.custAdds);
        mTextViewPhone = findViewById(R.id.custPhone);
        mTextViewDetails = findViewById(R.id.text_service_details);
        mTextViewId = findViewById(R.id.serivice_id);
        mTextViewStatus = findViewById(R.id.text_service_status);
        mTextViewType = findViewById(R.id.text_service_type);

        mTextViewTotal = findViewById(R.id.textViewTotalValue);
        spinnerProduct = findViewById(R.id.spinner_product);
        spinnerEquipmentType = findViewById(R.id.spinner_equipment_type);
        spinnerMake = findViewById(R.id.spinner_make);
        spinnerModel = findViewById(R.id.spinner_model);

        mEditTextDefects = findViewById(R.id.editText_defects);
        mEditTextEstimation = findViewById(R.id.editText_estimation);
        mEditTextRemarks = findViewById(R.id.editText_remarks);

        mRadioGroupStatus = findViewById(R.id.radioStatus);
        mRadioButtonComplete = findViewById(R.id.radioComplete);
        mRadioButtonIncomplete = findViewById(R.id.radioInComplete);
        mRadioButtonPending  = findViewById(R.id.radioPendingSpares);
        mRadioButtonObservation = findViewById(R.id.radioUnderObservation);
        mRadioButtonSolnProvided = findViewById(R.id.radioWorkingSolnProvided);

        mProgressBar = findViewById(R.id.progress_circular);

        CSRSubmitButton = findViewById(R.id.submitButton);

        mActionBar = getSupportActionBar();
    }

    @Override
    protected void initData() {
        setUpNetwork();
        getAllProducts();
        mAssignedTasksModel = getIntent().getParcelableExtra("DATA");
        if(mAssignedTasksModel!=null)
        {
            mTextViewName.setText(mAssignedTasksModel.getName());
            mTextViewPhone.setText(mAssignedTasksModel.getPhone());
            mTextViewAdds.setText(mAssignedTasksModel.getAdds());
            mTextViewDetails.setText(mAssignedTasksModel.getDetails());
            mTextViewType.setText(mAssignedTasksModel.getType());
            mTextViewId.setText(mAssignedTasksModel.getTask_id());
            mTextViewStatus.setText(mAssignedTasksModel.getStatus());
        }
        if(mActionBar!=null){
            mActionBar.setTitle("Customer And Service details");
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setShowHideAnimationEnabled(true);
        }

    }

    @Override
    protected void initListener() {
        CSRSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = SharedPrefsHelper.getInstance().get("ID", null);
                mCSRModel = new CSRModel();
                mCSRModel.setCustAdds(mAssignedTasksModel.getAdds());
                mCSRModel.setCustName(mAssignedTasksModel.getName());
                mCSRModel.setCustPhone(mAssignedTasksModel.getPhone());
                mCSRModel.setDefects(mEditTextDefects.getText().toString());
                mCSRModel.setEngrRemarks(mEditTextRemarks.getText().toString());
                mCSRModel.setServDetails(mAssignedTasksModel.getDetails());
                mCSRModel.setmCustID(mAssignedTasksModel.getCust_id());
                mCSRModel.setExecID(id);
                mCSRModel.setProduct(product);
                mCSRModel.setEquipmentType(equipmentType);
                mCSRModel.setMake(make);
                mCSRModel.setModel(model);
                mCSRModel.setDateTimel(System.currentTimeMillis());
                mCSRModel.setTaskID(mAssignedTasksModel.getTask_id());
                mCSRModel.setTotal(mEditTextEstimation.getText().toString());

                int selectedId = mRadioGroupStatus.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioStatusButton = findViewById(selectedId);
               mCSRModel.setStatusAfterService(radioStatusButton.getText().toString());

               mProgressBar.setVisibility(View.VISIBLE);
               Call<CSRResponseModel> csrResponseModelCall = apiInterface.updateCSR(mCSRModel);
               csrResponseModelCall.enqueue(new Callback<CSRResponseModel>() {
                   @Override
                   public void onResponse(Call<CSRResponseModel> call, Response<CSRResponseModel> response) {
                       mProgressBar.setVisibility(View.GONE);
                       if(response.isSuccessful() && response.body().isStatus())
                       {
                           Toast.makeText(CustomerDetailsActivity.this, "CSR update Success", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                       else {
                           Toast.makeText(CustomerDetailsActivity.this, "CSR update Fail", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<CSRResponseModel> call, Throwable t) {
                       mProgressBar.setVisibility(View.GONE);
                       Toast.makeText(CustomerDetailsActivity.this, "CSR update Fail, Please try again", Toast.LENGTH_SHORT).show();

                   }
               });

            }
        });

        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product = mOnlyProducts.get(position).getName();
                getProductDetails(mOnlyProducts.get(position).getPid());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model = modelList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                make = makeList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEquipmentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equipmentType = mEqList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mEditTextEstimation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextViewTotal.setText(mEditTextEstimation.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }

    private HashMap<String, String> makeMap, modelMap, eqMap;
    List<String> makeList = new ArrayList<>();
    List<String> modelList = new ArrayList<>();
    List<String> mEqList = new ArrayList<>();

    private void getProductDetails(String id)
    {
        Call<List<ProductDetails>> listCall = apiInterface.getProductDetails(id);
        listCall.enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if(response.isSuccessful()){
                  makeMap = new HashMap<>();
                  modelMap = new HashMap<>();
                  eqMap = new HashMap<>();
                  if(response.body().size()>0) {
                      for (int i = 0; i < response.body().size(); i++) {
                          makeMap.put(response.body().get(i).getMakename(), response.body().get(i).getMakename());
                          modelMap.put(response.body().get(i).getModelname(), response.body().get(i).getModelname());
                          eqMap.put(response.body().get(i).getName(), response.body().get(i).getName());
                      }


                      for (Map.Entry<String,String> entry : makeMap.entrySet()){
                          makeList.add(entry.getValue());
                      }

                      for (Map.Entry<String,String> entry : modelMap.entrySet()){
                          modelList.add(entry.getValue());
                      }

                      for (Map.Entry<String,String> entry : eqMap.entrySet()){
                          mEqList.add(entry.getValue());
                      }

                      try {
                          ArrayAdapter aa = new ArrayAdapter(CustomerDetailsActivity.this, android.R.layout.simple_spinner_item, makeList);
                          aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          spinnerMake.setAdapter(aa);


                          ArrayAdapter aa2 = new ArrayAdapter(CustomerDetailsActivity.this, android.R.layout.simple_spinner_item, modelList);
                          aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          spinnerModel.setAdapter(aa2);

                          ArrayAdapter aa3 = new ArrayAdapter(CustomerDetailsActivity.this, android.R.layout.simple_spinner_item, mEqList);
                          aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          spinnerEquipmentType.setAdapter(aa3);
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {

            }
        });
    }

    private void getAllProducts()
    {
       Call<List<ProductsOnlyModel>> listCall = apiInterface.getAllProducts("name");
       listCall.enqueue(new Callback<List<ProductsOnlyModel>>() {
           @Override
           public void onResponse(Call<List<ProductsOnlyModel>> call, Response<List<ProductsOnlyModel>> response) {
               if(response.isSuccessful())
               {
                   pnames = new ArrayList<>();
                   mOnlyProducts = response.body();
                   if(mOnlyProducts!=null)
                       for(int i = 0; i<mOnlyProducts.size(); i++)
                       {
                           pnames.add(mOnlyProducts.get(i).getName());
                       }
                   ArrayAdapter aa = new ArrayAdapter(CustomerDetailsActivity.this, android.R.layout.simple_spinner_item, pnames);
                   aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   spinnerProduct.setAdapter(aa);
               }
           }

           @Override
           public void onFailure(Call<List<ProductsOnlyModel>> call, Throwable t) {

           }
       });
    }


}
