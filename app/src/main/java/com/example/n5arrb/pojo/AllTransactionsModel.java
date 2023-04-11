package com.example.n5arrb.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllTransactionsModel {

    private List<AllTransaction> AllTransactions ;
    private TotalAmount TotalAmount ;


    public List<AllTransaction> getAllTransactions() {
        return AllTransactions;
    }

    public void setAllTransactions(List<AllTransaction> allTransactions) {
        AllTransactions = allTransactions;
    }

    public com.example.n5arrb.pojo.TotalAmount getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(com.example.n5arrb.pojo.TotalAmount totalAmount) {
        TotalAmount = totalAmount;
    }
}



