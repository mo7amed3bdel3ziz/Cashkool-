package com.example.n5arrb.network;
import com.example.n5arrb.pojo.AllClientsModel;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionsServiceRetrofit {
    private  static final String Base_url="http://kashcool.tbico.cloud/";
    private TransactionsApiService apiService;
    private static TransactionsServiceRetrofit Instance;
    private Retrofit retrofit;
    private TransactionsServiceRetrofit() {
        retrofit=new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
       // apiService=retrofit.create(TransactionsApiService.class);
    }


    public static TransactionsServiceRetrofit getInstans() {
        if( null ==Instance)
            Instance=new TransactionsServiceRetrofit();
        return Instance;
    }

  // public Observable<List<AllClientsModel>> getnews() {
  //     return apiService.getAllClints();

  // }

    public TransactionsApiService getApiService(){
        return  apiService=retrofit.create(TransactionsApiService.class);
    }

}
