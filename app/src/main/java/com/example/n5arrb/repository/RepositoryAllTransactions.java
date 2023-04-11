package com.example.n5arrb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.AllTransaction;
import com.example.n5arrb.pojo.AllTransactionsModel;
import com.example.n5arrb.pojo.TotalAmount;
import com.example.n5arrb.pojo.UserClientPhones;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryAllTransactions {

    MutableLiveData<List<AllTransaction>>allTransactionliveData=new MutableLiveData<>();
    MutableLiveData <TotalAmount>totalLiveData=new MutableLiveData<>();



    public void GetAllTransactions(UserClientPhones phones){

        Observable GetTransactions= TransactionsServiceRetrofit.getInstans().
                getApiService().getAllTransactions(phones)
                . subscribeOn(Schedulers.io())
                .map(new Function<AllTransactionsModel, AllTransactionsModel>() {
                    @Override
                    public AllTransactionsModel apply(AllTransactionsModel allTransactionsModel) throws Throwable {
                        AllTransactionsModel allTransactionsModels=allTransactionsModel;
                        Double Credit=0.0;
                        Double Depits=0.0;
                        Double netbalance=0.0;
                        if (allTransactionsModels.getTotalAmount().getTotalCredits()!=null){
                            Credit=  allTransactionsModels.getTotalAmount().getTotalCredits();

                        }
                        allTransactionsModels.getTotalAmount().setTotalCredits(Credit);
                        if (allTransactionsModels.getTotalAmount().getTotalDepits()!=null){
                            Depits=  allTransactionsModels.getTotalAmount().getTotalDepits();

                        }
                        allTransactionsModels.getTotalAmount().setTotalDepits(Depits);
                       //Credit=allTransactionsModel.getTotalAmount().getTotalCredits();
                       //Depits=allTransactionsModel.getTotalAmount().getTotalDepits();

                        netbalance= Credit-Depits;

                        allTransactionsModel.getTotalAmount().setNetbalance(netbalance);


                        return allTransactionsModels;
                    }
                })
                . observeOn(AndroidSchedulers.mainThread());

        Observer<AllTransactionsModel>listObserver= new Observer<AllTransactionsModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onNext(@NonNull AllTransactionsModel allTransactionsModel) {
                allTransactionliveData.setValue(allTransactionsModel.getAllTransactions());

                totalLiveData.setValue( allTransactionsModel.getTotalAmount());
            }
            @Override
            public void onError(@NonNull Throwable e) {
             //   Log.d("yousif",e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        };
        GetTransactions.subscribe(listObserver);
    }


    public MutableLiveData<List<AllTransaction>> getAllTransactionliveData() {
        return allTransactionliveData;
    }

    public MutableLiveData<TotalAmount> getTotalLiveData() {
        return totalLiveData;
    }

}
