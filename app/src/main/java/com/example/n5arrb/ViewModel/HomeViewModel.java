package com.example.n5arrb.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.n5arrb.pojo.AllClientsModel;
import com.example.n5arrb.pojo.TotalAmountModel;
import com.example.n5arrb.repository.RepositoryHome;

import java.util.List;

public class HomeViewModel extends ViewModel {

    RepositoryHome repositoryHome;
    MutableLiveData<List<AllClientsModel>> getallClintsLive = new MutableLiveData<>();
    MutableLiveData<TotalAmountModel> getTotalAmountLivedata = new MutableLiveData<>();


    //SingleLiveEvent<String> mutableLiveData = new SingleLiveEvent<>();


    public HomeViewModel() {
     repositoryHome=new RepositoryHome();
   }

   public void getallClint(String numer,  Context context){
       repositoryHome.getAllClints(numer,context);
       getallClintsLive= repositoryHome.getClientsModelMutableLiveData();
   }
    public void getTotalAmounts(String numers){
        repositoryHome.getTotalAmount(numers);
        getTotalAmountLivedata= repositoryHome.getTotalAmountss();
    }

    public MutableLiveData<List<AllClientsModel>> getGetallClintsLive() {
        return getallClintsLive;
    }

    public MutableLiveData<TotalAmountModel> getGetTotalAmountLivedata() {
        return getTotalAmountLivedata;
    }


}

