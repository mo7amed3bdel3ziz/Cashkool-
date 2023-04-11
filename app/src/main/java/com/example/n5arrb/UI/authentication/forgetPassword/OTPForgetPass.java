package com.example.n5arrb.UI.authentication.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.oTP.OtpVerifyActivity;
import com.example.n5arrb.UI.authentication.oTP.SendOTPActivity;
import com.example.n5arrb.databinding.ActivityOtpforgetPassBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.OtpModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OTPForgetPass extends AppCompatActivity {
ActivityOtpforgetPassBinding binding;
    String number;
    String egyptcode="+2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpforget_pass);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otpforget_pass);
        binding. progressBar3.setVisibility(View.GONE);
        Toast.makeText(OTPForgetPass.this, "not have acc", Toast.LENGTH_SHORT).show();
       // Intent i = new Intent(OTPForgetPass.this, ForgetPassActivity.class);
       // i.putExtra("phones", number);
       // startActivity(i);
        binding.button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isvaled() == true) {
                    binding. progressBar3.setVisibility(View.VISIBLE);
                    number = egyptcode +binding. editTextPhone5.getText().toString().trim();
                    Observable otb = TransactionsServiceRetrofit.getInstans().getApiService().SendToChangePass(number).
                            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    Observer<OtpModel> observer = new Observer<OtpModel>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull OtpModel otpModel) {

                            if (otpModel.getState() == 200) {
                                Toast.makeText(OTPForgetPass.this, "Already this phone have acc", Toast.LENGTH_SHORT).show();

                                binding.progressBar3.setVisibility(View.GONE);
                                //false->log in
                                //
                                Intent i = new Intent(OTPForgetPass.this, SendOTBForgetPass.class);
                                i.putExtra("phone", number);
                                startActivity(i);
                                finish();
                                // Intent i =new Intent(MainActivity.this, MainActivity4.class);
                                // startActivity(i);
                            } else if(otpModel.getState()==400){
                                Toast.makeText(OTPForgetPass.this, "invalid", Toast.LENGTH_SHORT).show();
                                binding.progressBar3.setVisibility(View.GONE);
                                //true-> vir
                                // SharedPreferences sharedPreferences=getSharedPreferences("user2.getUid()",MODE_PRIVATE);
                                // SharedPreferences.Editor edit=sharedPreferences.edit();
                                // edit.putString("phone",number);
                                // edit.apply();
                              // binding.  progressBar3.setVisibility(View.GONE);
                            //  Intent i = new Intent(OTPForgetPass.this, SendOTBForgetPass.class);
                            //  i.putExtra("phones", number);
                            //  startActivity(i);
                            //  finish();
                            }else if(otpModel.getState()==401){
                                binding.progressBar3.setVisibility(View.GONE);
                                Toast.makeText(OTPForgetPass.this, "not have acc", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            binding.progressBar3.setVisibility(View.GONE);
                            Toast.makeText(OTPForgetPass.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onComplete() {

                        }

                    };
                    otb.subscribe(observer);

                }
            }
        });


    }

    public Boolean isvaled() {


        if (  binding. editTextPhone5.getText().toString().isEmpty()) {
            binding. editTextPhone5.setError("Enter phone");
            return false;
        }

        String s =     binding.editTextPhone5.getText().toString().trim();
        if (s.length() <= 10) {
            binding. editTextPhone5.setError("Phone is not valid");
            return false;
        }
        if (s.length() >= 12) {
            binding.   editTextPhone5.setError("Phone is big");
            return false;
        }



        {
            return true;
        }
    }
}