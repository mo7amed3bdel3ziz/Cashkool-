package com.example.n5arrb.UI.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.n5arrb.MainActivity;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.forgetPassword.OTPForgetPass;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.contactesAccess.ContactsActivity;
import com.example.n5arrb.databinding.ActivityLoginBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.LoginModel;
import com.example.n5arrb.pojo.auth.OtpModel;

import java.util.HashMap;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Login extends AppCompatActivity {
    String number;
    String egyptcode="+2";
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.textView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, ContactsActivity.class);
                startActivity(i);

            }
        });
        binding.progressBar5.setVisibility(View.GONE);
        binding.SigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isvaled() == true) {
                    binding.progressBar5.setVisibility(View.VISIBLE);
                    String phone = binding.phoneLog.getText().toString();
                    number = egyptcode + phone;
                    String pass = binding.PasswordLog.getText().toString();

                    HashMap<Object, Object> logMap = new HashMap<>();
                    logMap.put("PhoneNumber", number);
                    logMap.put("Password", pass);

                    Observable log = TransactionsServiceRetrofit.getInstans().getApiService()
                            .LoginVerify(logMap)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    Observer<LoginModel> loginModelObserver = new Observer<LoginModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull LoginModel loginModel) {
                            if (loginModel.isSuccess() == true) {

                              //  SharedPreferences sharedPreferenclanguage = getSharedPreferences("language", MODE_PRIVATE);
                              //  SharedPreferences.Editor edits = sharedPreferenclanguage.edit();
                              //  edits.putString("lang","ar");
                              //  edits.apply();
                              //  setLocale("ar");


                                SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString("phone", "+2" + phone);
                                edit.apply();


                                binding.progressBar5.setVisibility(View.GONE);

                                Intent i = new Intent(Login.this, MainActivity5.class);
                                startActivity(i);
                                finish();

                            }
                            if (loginModel.isSuccess() == false) {

                                binding.progressBar5.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Invalid User or Password", Toast.LENGTH_SHORT).show();
                            }
                            // else
                            // Toast.makeText(Login.this, "noNumer", Toast.LENGTH_SHORT).show();

                            //   num//401
                            //200 true
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            binding.progressBar5.setVisibility(View.GONE);
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onComplete() {


                        }
                    };


                    log.subscribe(loginModelObserver);


                }
            }
        });

    }
    public Boolean isvaled() {



            if (binding.phoneLog.getText().toString().isEmpty()) {
                binding.phoneLog.setError("Enter phone");
                return false;
            }

            String s = binding.phoneLog.getText().toString().trim();
            if (s.length() <= 10) {
                binding.phoneLog.setError("Phone not valid");
                return false;
            }
            if (s.length() >= 12) {
                binding.phoneLog.setError("Phone is big");
                return false;
            }

        if (binding.PasswordLog.getText().toString().isEmpty()) {
            binding.PasswordLog.setError("Enter pass");
            return false;

        }

        String pass = binding.PasswordLog.getText().toString().trim();
        if (pass.length() <= 7) {
            binding.PasswordLog.setError("Phone not valid");
            return false;
        }

            {
                return true;
            }
        }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        // Intent refresh = new Intent(SplashActivity.this, MainActivity5.class);
        // startActivity(refresh);
    }
    }
