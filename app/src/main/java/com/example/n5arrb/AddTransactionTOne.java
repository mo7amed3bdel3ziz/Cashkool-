package com.example.n5arrb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.n5arrb.pojo.AllTransaction;

import java.util.ArrayList;

public class AddTransactionTOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_tone);
        ArrayList<AllTransaction>arrayList=new ArrayList<>();
       // arrayList.add(new AllTransaction() );

    }
}