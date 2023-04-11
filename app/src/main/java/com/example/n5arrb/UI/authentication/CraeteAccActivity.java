package com.example.n5arrb.UI.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.n5arrb.MainActivity4;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.UI.splash.SplashActivity;
import com.example.n5arrb.databinding.ActivityCraeteAccBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.LoginModel;

import java.util.HashMap;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CraeteAccActivity extends AppCompatActivity {
ActivityCraeteAccBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craete_acc);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_craete_acc);
        binding.progressBar4.setVisibility(View.GONE);
          Intent i=getIntent();
          String phones=i.getStringExtra("phoneotb");

       // String phones="+20107876242";

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar4.setVisibility(View.VISIBLE);
                if (isvaled() == true) {
                    String name = binding.nameCraete.getText().toString();
                    String pass = binding.passCraete.getText().toString();
                    String Passcon = binding.conPassCraete.getText().toString();


                    HashMap<Object, Object> craeteMap = new HashMap<>();

                    craeteMap.put("Name", name);
                    craeteMap.put("Password", pass);
                    craeteMap.put("ConfirmPassword", Passcon);
                    craeteMap.put("PhoneNumber", phones);


                    Single craete = TransactionsServiceRetrofit.getInstans().getApiService().CreateAcc(craeteMap)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    SingleObserver<LoginModel> craetModelObserver = new SingleObserver<LoginModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull LoginModel loginModel) {
                            if (loginModel.isSuccess() == true) {
                                binding.progressBar4.setVisibility(View.GONE);
                               // SharedPreferences sharedPreferenclanguage = getSharedPreferences("language", MODE_PRIVATE);
                               // SharedPreferences.Editor edits = sharedPreferenclanguage.edit();
                               // edits.putString("lang","ar");
                               // edits.apply();
                               // setLocale("ar");

                            //   SharedPreferences sharedPreferenclanguage = getSharedPreferences("language", MODE_PRIVATE);
                            //   SharedPreferences.Editor edits = sharedPreferenclanguage.edit();
                            //   edits.putString("lang","ar");
                            //   edits.apply();
                            //   setLocale("ar");

                                SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString("phone", phones);
                                edit.apply();
                                Intent i = new Intent(CraeteAccActivity.this, MainActivity5.class);
                                startActivity(i);
                                finish();
                            }else if(loginModel.isSuccess() == false) {

                                binding.progressBar4.setVisibility(View.GONE);
                                Toast.makeText(CraeteAccActivity.this, "Invalid  Password or confirmPassword", Toast.LENGTH_SHORT).show();

                            }
                          //  if (loginModel.isSuccess() == false) {
                          //      Toast.makeText(CraeteAccActivity.this, "Invalid User or Password", Toast.LENGTH_SHORT).show();
                          //  }

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            binding.progressBar4.setVisibility(View.GONE);
                            Toast.makeText(CraeteAccActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    };
                    //{
                     //  @Override
                     //  public void onSubscribe(@NonNull Disposable d) {

                     //  }

                     //  @Override
                     //  public void onNext(@NonNull LoginModel loginModel) {
                     //      if (loginModel.isSuccess() == true) {
                     //          binding.progressBar4.setVisibility(View.GONE);
                     //          SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                     //          SharedPreferences.Editor edit = sharedPreferences.edit();
                     //          edit.putString("phone", phones);
                     //          edit.apply();
                     //          Intent i = new Intent(CraeteAccActivity.this, MainActivity5.class);
                     //          startActivity(i);
                     //          finish();

                     //      }
                     //      if (loginModel.isSuccess() == false) {

                     //          Toast.makeText(CraeteAccActivity.this, "Invalid User or Password", Toast.LENGTH_SHORT).show();
                     //      }
                     //      // else
                     //      // Toast.makeText(Login.this, "noNumer", Toast.LENGTH_SHORT).show();

                     //      //   num//401
                     //      //200 true
                     //  }

                     //  @Override
                     //  public void onError(@NonNull Throwable e) {

                     //  }

                     //  @Override
                     //  public void onComplete() {

                     //  }
                  //  };

                    craete.subscribe(craetModelObserver);

                }else {
                    binding.progressBar4.setVisibility(View.GONE);

                }
            }
        });

    }
     public Boolean isvaled() {



         if (binding.nameCraete.getText().toString().isEmpty()) {
             binding.nameCraete.setError("Enter phone");
             return false;
         }



         if (binding.passCraete.getText().toString().isEmpty()) {
             binding.passCraete.setError("Enter pass");
             return false;

         }
         String s = binding.passCraete.getText().toString().trim();
         if (s.length() <= 7) {
             binding.passCraete.setError("pass not valid");
             return false;
         }
         if (binding.conPassCraete.getText().toString().isEmpty()) {
             binding.conPassCraete.setError("Enter  ConPass");
             return false;

         }

         String ConPasss = binding.conPassCraete.getText().toString().trim();
         if ( ConPasss.length() <= 7) {

             binding.conPassCraete.setError("pass not valid");
             return false;
         }

        // if ()




       // if (binding.conPassCraete.getText().toString()!=binding.passCraete.getText().toString()) {
       //     binding.conPassCraete.setError("pass is not equal ConPass ");
       //     return false;
       // }
       // String pass=binding.passCraete.getText().toString().trim();
       // String copass=binding.conPassCraete.getText().toString().trim();
         if (!binding.passCraete.getText().toString().trim().equals(binding.conPassCraete.getText().toString().trim())) {
            // Toast.makeText(CraeteAccActivity.this,  "pass"+pass, Toast.LENGTH_SHORT).show();
            // Toast.makeText(CraeteAccActivity.this,  "conpass"+copass, Toast.LENGTH_SHORT).show();

             binding.conPassCraete.setError("pass is not equal ConPass ");
             return false;
         }
        // if (binding.passCraete.getText().toString().trim()!=binding.conPassCraete.getText().toString().trim()) {
        //     binding.conPassCraete.setError("pass is not equal ConPass ");
        //     return false;
        // }

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