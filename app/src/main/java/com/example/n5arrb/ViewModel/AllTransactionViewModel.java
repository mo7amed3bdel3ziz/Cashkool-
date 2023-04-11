package com.example.n5arrb.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.n5arrb.pojo.AllClientsModel;
import com.example.n5arrb.pojo.AllTransaction;
import com.example.n5arrb.pojo.TotalAmount;
import com.example.n5arrb.pojo.TotalAmountModel;
import com.example.n5arrb.pojo.UserClientPhones;
import com.example.n5arrb.repository.RepositoryAllTransactions;

import java.util.List;

public class AllTransactionViewModel extends ViewModel {
    RepositoryAllTransactions allTransactions;
    MutableLiveData<List<AllTransaction>>allTransactionliveDataVm=new MutableLiveData<>();
    MutableLiveData<TotalAmount>totalLiveDataVm=new MutableLiveData<>();


    public AllTransactionViewModel() {
        allTransactions=new RepositoryAllTransactions();
    }

    public void getTransactions(UserClientPhones phones){
        allTransactions.GetAllTransactions(phones);
        allTransactionliveDataVm= allTransactions.getAllTransactionliveData();
        totalLiveDataVm=allTransactions.getTotalLiveData();
    }


    public MutableLiveData<List<AllTransaction>> getAllTransactionliveDataVm() {
        return allTransactionliveDataVm;
    }

    public MutableLiveData<TotalAmount> getTotalLiveDataVm() {
        return totalLiveDataVm;
    }


}
