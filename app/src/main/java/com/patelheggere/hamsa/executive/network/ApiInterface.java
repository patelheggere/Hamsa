package com.patelheggere.hamsa.executive.network;


import com.patelheggere.hamsa.executive.model.AssignedTasksModel;
import com.patelheggere.hamsa.executive.model.CSRModel;
import com.patelheggere.hamsa.executive.model.CSRResponseModel;
import com.patelheggere.hamsa.executive.model.ExecVerifyModel;
import com.patelheggere.hamsa.executive.model.ProductDetails;
import com.patelheggere.hamsa.executive.model.ProductModel;
import com.patelheggere.hamsa.executive.model.ProductsOnlyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */

   /* //mine AIzaSyD_Zbbwx7aYQaAWnl5O2Dv4-6r2G3dhEUI
    //ind AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww
    @GET("api/place/nearbysearch/json?sensor=true&rankby=distance&key=AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww")
    Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location);
    //Call<Place> getNearbyPlaces(@Query("location") String location);

    @GET("api/place/nearbysearch/json?sensor=true&rankby=distance&key=AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww")
    Call<Place> getNearbyPlacesWithToken(@Query("location") String location, @Query("pagetoken") String token);*/

    // with type
    //Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location);

   // Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location, @Query("radius") int radius);

   // @GET("beneficiary/getByMobile.php")
  //  Call<BeneficiaryModel> getByMobile(@Query("mobile") String mobile);

    @GET("getTaskByExeId.php")
    Call<List<AssignedTasksModel>> getTaskAssignedToExe(@Query("id") String id);

    @GET("VerifyUser.php")
    Call<ExecVerifyModel> verifyUser(@Query("uname") String uname, @Query("pwd") String pwd);

    @GET("getAllProducts.php")
    Call<List<ProductsOnlyModel>> getAllProducts(@Query("name") String name);

    @GET("getProductDetails.php")
    Call<List<ProductDetails>> getProductDetails(@Query("pid") String name);

    @POST("updateCSR.php")
    Call<CSRResponseModel> updateCSR(@Body CSRModel csrModel);

    @GET("http://stage-central.oustme.com/rest/services/course/getAdaptiveCourseData/3007")
    Call<Object> getData(@Header("org-id") String orgId);

   // @GET("beneficiary/getByEPIC.php")
   // Call<BeneficiaryModel> getByEPIC(@Query("epic") String epic);


}
