package com.example.n5arrb.UI.authentication.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.CraeteAccActivity;
import com.example.n5arrb.UI.authentication.oTP.OtpVerifyActivity;
import com.example.n5arrb.databinding.ActivitySendOtbforgetPassBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.OtpModel;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SendOTBForgetPass extends AppCompatActivity {
ActivitySendOtbforgetPassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otbforget_pass);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_otbforget_pass);
        Intent i=getIntent();
        String phones=i.getStringExtra("phone");
        binding .  progressBar2.setVisibility(View.GONE);
        binding .button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding .   progressBar2.setVisibility(View.VISIBLE);
                //   if (isvaled() == true) {
                //Intent i =new Intent(MainActivity2.this, AllTransactionForOneClaActivity.class);
                String v =  binding .NumVerify.getText().toString();

                Toast.makeText( SendOTBForgetPass.this, phones, Toast.LENGTH_SHORT).show();
                Toast.makeText( SendOTBForgetPass.this, v, Toast.LENGTH_SHORT).show();

                HashMap<Object, Object> map = new HashMap<>();

                map.put("PhoneNumber", phones);
                map.put("Code", v);
                Observable otb = TransactionsServiceRetrofit.getInstans().getApiService().sendVerifyOtp(map).
                        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

                Observer<OtpModel> observer = new Observer<OtpModel>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull OtpModel otpModel) {

                        if (otpModel.isSuccess() == false) {
                            //false ->re enter code (resend or enter code)
                            binding .  progressBar2.setVisibility(View.GONE);
                            Toast.makeText( SendOTBForgetPass.this, "resend or enter code", Toast.LENGTH_SHORT).show();
                        } else  if(otpModel.isSuccess() == true){
                            //true-> create
                            Toast.makeText( SendOTBForgetPass.this, " create acc", Toast.LENGTH_SHORT).show();
                            binding .   progressBar2.setVisibility(View.GONE);
                            Intent i = new Intent( SendOTBForgetPass.this, ForgetPassActivity.class);
                            i.putExtra("phone", phones);
                            startActivity(i);
                            finish();

                        }


                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                        binding . progressBar2.setVisibility(View.GONE);
                        Toast.makeText( SendOTBForgetPass.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                otb.subscribe(observer);
                // Intent x =new Intent(MainActivity2.this, MainActivity5.class);
                // startActivity(x);
                //   }
            }
        });

    }
}