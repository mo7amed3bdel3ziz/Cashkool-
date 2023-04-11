package com.example.n5arrb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.n5arrb.UI.authentication.Login;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.databinding.ActivityMain4Binding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.LoginModel;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity4 extends AppCompatActivity {
Button button;
ActivityMain4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
            // setContentView(R.layout.activity_main4);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main4);

}
}