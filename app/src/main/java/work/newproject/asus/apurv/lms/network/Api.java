package work.newproject.asus.apurv.lms.network;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import work.newproject.asus.apurv.lms.network.model.AddNewForm;
import work.newproject.asus.apurv.lms.network.model.GetList;
import work.newproject.asus.apurv.lms.network.model.LoginModel;
import work.newproject.asus.apurv.lms.network.model.getGrfModel;
import work.newproject.asus.apurv.lms.network.model.send_form_model;

public interface Api {


    @FormUrlEncoded
    @POST("user_login")
    Single<LoginModel> login(@Field("mobile") String mobile, @Field("password") String password, @Field("user_type") String user_type);



    @FormUrlEncoded
    @POST("recieving_form")
    Single<AddNewForm> addForm(@Field("color") String color, @Field("receiving_date_lab") String receiving_date_lab, @Field("packing") String packing
    ,@Field("plastic") String plastic,@Field("deposit_by") String deposit_by,@Field("date_of_collection") String date_of_collection
    ,@Field("collected_by") String collected_by,@Field("type_of_sample") String type_of_sample,@Field("address") String address
    ,@Field("sampling_point") String sampling_point,@Field("type") String type,@Field("qr_id") String qr_ic,@Field("login_id") String login_id,@Field("login_type") String login_type);

    @POST("get_reieving_data")
    Single<GetList> getList();





    @POST("get_jrf")
    Single<getGrfModel> getGrfLIst();

    @FormUrlEncoded
    @POST("asign_data")
    Single<AddNewForm> sendTestForm(@Field("jrf_id")String jrf_id,@Field("jrf_name")String jrf_name,@Field("qr_id")String qr_id,@Field("json")String json);

    @FormUrlEncoded
   @POST("get_jrf_asign_data")
    Single<GetList> getGrf(@Field("id")String id);


    @FormUrlEncoded
    @POST("return_json")
    Single<send_form_model> sendForm(@Field("jrf_id")String jrf_id,@Field("qr_id")String qrID);

    @FormUrlEncoded
    @POST("asign_data1")
    Single<AddNewForm> sendGrfForm(@Field("jrf_id")String jrf_id,@Field("qr_id")String qr_id,@Field("json")String json);

}





