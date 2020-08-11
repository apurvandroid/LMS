package work.newproject.asus.apurv.lms.network;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import work.newproject.asus.apurv.lms.network.model.AddNewForm;
import work.newproject.asus.apurv.lms.network.model.LoginModel;

public interface Api {


    @FormUrlEncoded
    @POST("user_login")
    Single<LoginModel> login(@Field("mobile") String mobile, @Field("password") String password, @Field("user_type") String user_type);



    @FormUrlEncoded
    @POST("recieving_form")
    Single<AddNewForm> addForm(@Field("color") String color, @Field("receiving_date_lab") String receiving_date_lab, @Field("packing") String packing
    ,@Field("qty") String qty,@Field("mobile") String mobile,@Field("deposit_by") String deposit_by,@Field("date_of_collection") String date_of_collection
    ,@Field("collected_by") String collected_by,@Field("type_of_sample") String type_of_sample,@Field("address") String address
    ,@Field("name_of_souce") String name_of_souce,@Field("type") String type);


}


