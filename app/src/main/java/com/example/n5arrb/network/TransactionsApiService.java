package com.example.n5arrb.network;

import com.example.n5arrb.pojo.AddTransactions;
import com.example.n5arrb.pojo.AllClientsModel;
import com.example.n5arrb.pojo.AllTransactionsModel;
import com.example.n5arrb.pojo.NotifayModel;
import com.example.n5arrb.pojo.ResponseTransactionModel;
import com.example.n5arrb.pojo.TotalAmountModel;
import com.example.n5arrb.pojo.UserClientPhones;

import com.example.n5arrb.pojo.auth.LoginModel;
import com.example.n5arrb.pojo.auth.OtpModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TransactionsApiService {

    //  @Headers("Content-Type: application/json")
    //  @POST("api/Transaction/GetAllClients")
    //  Single<List<AllClientsModel>> getAllClints(
    //          @Body String s);

    @Headers("Content-Type: application/json")
    @POST("api/Account/changePassword")
    Single<LoginModel> changePassword(
            @Body HashMap changePass);


    //text/xml
    @Headers("Content-Type: application/json")
    @POST("api/Transaction/GetAllClients")
    Observable<List<AllClientsModel>> getAllClintsO(
            @Body String s);




    @Headers("Content-Type: application/json")
    @POST("api/Transaction/AllClients_N")
    Single<List<AllClientsModel>> getAllClints(
            @Body String s);


    @Headers("Content-Type: application/json")
    @POST("api/transaction/GetTotalAmount")
    Observable<TotalAmountModel> getTotalAmount(
            @Body String s);

    @Headers("Content-Type: application/json")
    @POST("api/transaction/GetAllTransactions")
    Observable<AllTransactionsModel> getAllTransactions(
            @Body UserClientPhones phones);

    @Headers("Content-Type: application/json")
    @POST("api/Transaction/AddTransaction")
    Call<ResponseTransactionModel> AddTransaction(
            @Body AddTransactions add);
    // @Headers("Content-Type: application/json")
    // @POST("api/Transaction/AddTransaction")
    // Call<AllTransactionsModel> AddTransaction(
    //         @Body AddTransactions add);

    @Headers("Content-Type: application/json")
    @POST("api/Transaction/GetNotification_V2")
    Observable<List<NotifayModel>> getNotifay(
            @Body String phoneNotifay);

    @Headers("Content-Type: application/json")
    @POST("api/Otp/SendToChangePass")
    Observable<OtpModel>SendToChangePass(
            @Body String phone);

    @Headers("Content-Type: application/json")
    @POST("api/Otp/Send")
    Observable<OtpModel> sendOtp(
            @Body String phone);

    @Headers("Content-Type: application/json")
    @POST("api/Otp/Verify")
    Observable<OtpModel> sendVerifyOtp(
            @Body HashMap phone);

    @Headers("Content-Type: application/json")
    @POST("api/Account/login")
    Observable<LoginModel> LoginVerify(
            @Body HashMap phone);

    @Headers("Content-Type: application/json")
    @POST("api/Account/Register")
    Single<LoginModel> CreateAcc(
            @Body HashMap create);


    @Headers("Content-Type: application/json")
    @POST("api/Transaction/RejectTransaction")
    Observable<LoginModel>RejectTransaction(
            @Body int id);




}