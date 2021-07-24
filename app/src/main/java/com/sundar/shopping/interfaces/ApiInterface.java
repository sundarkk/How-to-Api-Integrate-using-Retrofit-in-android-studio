package com.sundar.shopping.interfaces;


import com.google.gson.JsonObject;

import org.json.JSONArray;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by sundar chaupal on 20/06/21.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> loginWithEmail(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("login_with") int login_with);

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> loginWithGoogle(@Field("email") String email,
                                     @Field("login_with") int login_with);

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> loginWithFacebook(@Field("email") String email,
                                       @Field("login_with") int login_with);

    @Multipart
    @POST("signup")
    Call<JsonObject> registerUser(@Part("name") RequestBody name,
                                  @Part MultipartBody.Part profile_pic,
                                  @Part("email") RequestBody email,
                                  @Part("mobile_number") RequestBody mobile_number,
                                  @Part("password") RequestBody password,
                                  @Part("dob") RequestBody dob, //date(2019-02-15)
                                  @Part("device_token") RequestBody device_token,
                                  @Part("device_id") RequestBody device_id
//                                 @Part("google_unique_id") RequestBody google_unique_id,
//                                 @Part("facebook_unique_id") RequestBody facebook_unique_id
    );

    @FormUrlEncoded
    @POST("otp/verification")
    Call<JsonObject> OtpVerification(@Field("email") String email,
                                     @Field("otp") String otp);


    @FormUrlEncoded
    @POST("resend/otp")
    Call<JsonObject> resendOtp(@Field("email") String email);

    @FormUrlEncoded
    @POST("forgot/password")
    Call<JsonObject> forgetPassword(@Field("email") String email,
                                    @Field("password") String password);


    @FormUrlEncoded
    @POST("home")
    Call<JsonObject> getHome(/*@Field("page_count") int page_count)*/ @Field("user_id") int user_id);


    @FormUrlEncoded
    @POST("product/detail")
    Call<JsonObject> getProductDetails(@Field("product_id") int product_id);

    @FormUrlEncoded
    @POST("view/more/setion_three")
    Call<JsonObject> getSectionThree(@Field("page_count") int page_count);

    @FormUrlEncoded
    @POST("view/more/section_two")
    Call<JsonObject> getSectionTwo(@Field("page_count") int page_count);


    @FormUrlEncoded
    @POST("getconfigurations")
    Call<JsonObject> getDevicesDeatiails(@Field("device_id") String device_id,
                                         @Field("device_token") String device_token,
                                         @Field("device_type") String device_type);


    @FormUrlEncoded
    @POST("category/list")
    Call<JsonObject> getCategoryList(@Field("page_count") int page_count);


    @FormUrlEncoded
    @POST("add/address")
    Call<JsonObject> getAddAddress(@Field("name") String name,
                                   @Field("mobile") String mobile,
                                   @Field("pincode") int pincode,
                                   @Field("state") String state,
                                   @Field("address") String address,
                                   @Field("town") String town,
                                   @Field("city") String city,
                                   @Field("type_of_address") String type_of_address,
                                   @Field("user_id") int user_id);

    //get/address/list
    @FormUrlEncoded
    @POST("get/address/list")
    Call<JsonObject> getAddressList(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("create/order")
    Call<JsonObject> getCreateOrder(@Field("product_id") int product_id,
                                    @Field("user_id") int user_id,
                                    @Field("total_amount") float total_amount,
                                    @Field("discount") String discount,
                                    @Field("discounted_amount") float discounted_amount,
                                    @Field("net_amount") float net_amount,
                                    @Field("quantity") int quantity,
                                    @Field("category_id") int category_id,
                                    @Field("subcategory_id") int subcategory_id,
                                    @Field("size_id") int size_id,
                                    @Field("color_id") int color_id);


    @FormUrlEncoded
    @POST("my/cart/list")
    Call<JsonObject> getMyCartList(@Field("user_id") int user_id);


    @FormUrlEncoded
    @POST("add/to/cart")
    Call<JsonObject> getAddToCart(@Field("product_id") int product_id,
                                  @Field("user_id") int user_id,
                                  @Field("quantity") String quantity,
                                  @Field("size_id") String size_id,
                                  @Field("color_id") String color_id);


    @FormUrlEncoded
    @POST("category/detail")
    Call<JsonObject> getCategoryDetail(@Field("category_id") String category_id,
                                       @Field("user_id") String user_id);






    @FormUrlEncoded
    @POST("category/detail")
    Call<JsonObject> getCategoryDetail(@Field("category_id") String category_id,
                                       @Field("subcategory_id") int subcategory_id,
                                       @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("add/to/wishlist")
    Call<JsonObject> getAddToWishlist(@Field("user_id") int user_id,
                                      @Field("product_id") int product_id);



    @FormUrlEncoded
    @POST("add/recent/items")
    Call<JsonObject> getAddRecentItems(@Field("user_id") int user_id,
                                       @Query("data") JSONArray array);


    @FormUrlEncoded
    @POST("wishlist")
    Call<JsonObject> getWishlist(@Field("user_id") int user_id);


    @FormUrlEncoded
    @POST("add/to/cart")
    Call<JsonObject> getAddToCcart(@Field("product_id") int product_id,
                                   @Field("user_id") int user_id,
                                   @Field("quantity") int quantity,
                                   @Field("size_id") int size_id,
                                   @Field("color_id") int color_id);



    @FormUrlEncoded
    @POST("remove/from/wishlist")
    Call<JsonObject> getWishlistRemove(@Field("wishlist_id") int wishlist_id);





    @FormUrlEncoded
    @POST("remove/from/cart")
    Call<JsonObject> getRemoveFromCart(@Field("user_id") int user_id,
                                       @Field("product_id") int product_id);





 //   @FormUrlEncoded
    @GET("get/colors")
    Call<JsonObject> getColors();

    @GET("get/size")
    Call<JsonObject> getSizes();

}
