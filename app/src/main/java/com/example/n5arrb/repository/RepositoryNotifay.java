package com.example.n5arrb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.AllTransactionsModel;
import com.example.n5arrb.pojo.NotifayModel;
import com.example.n5arrb.pojo.TotalAmount;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryNotifay {

    MutableLiveData<List<NotifayModel>> NotifayLiveData=new MutableLiveData<>();


    public void getNotifayRep(String phone){
        Observable getNotifay= TransactionsServiceRetrofit.getInstans().
                getApiService().getNotifay(phone)
                . subscribeOn(Schedulers.io())
                . observeOn(AndroidSchedulers.mainThread());


        Observer <List<NotifayModel>>listNotifay=new Observer<List<NotifayModel>>(){
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<NotifayModel> notifayModels) {

                NotifayLiveData.setValue(notifayModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        getNotifay.subscribe(listNotifay);


    }
    public MutableLiveData<List<NotifayModel>> getNotifayLiveData() {
        return NotifayLiveData;
    }
}
