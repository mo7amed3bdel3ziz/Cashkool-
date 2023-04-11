package com.example.n5arrb.UI.authentication.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.Login;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.databinding.ActivityForgetPassBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.LoginModel;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ForgetPassActivity extends AppCompatActivity {
    String phones;
    ActivityForgetPassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_forget_pass);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_pass);
         binding.progressBar12.setVisibility(View.GONE);
        Intent i=getIntent();
      phones=i.getStringExtra("phone");
        binding.  changePassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(ForgetPassActivity.this,  phones, Toast.LENGTH_SHORT).show();
                if (isvaled()==true){

                    apiChangePass();

                }else {}

            }
        });

    }
    public void apiChangePass(){

        binding.progressBar12.setVisibility(View.VISIBLE);

      //  Toast.makeText(ForgetPassActivity.this,  phones, Toast.LENGTH_SHORT).show();
        String Password=  binding.passeET.getText().toString().trim();
       String ConfirmPassword=  binding.passET.getText().toString().trim();

        HashMap<Object, Object> ChangeMap = new HashMap<>();

        ChangeMap.put("Password", Password);
        ChangeMap.put("ConfirmPassword", ConfirmPassword);
        ChangeMap.put("PhoneNumber",  phones);


        Single SingleChangePass= TransactionsServiceRetrofit.getInstans().
                getApiService().changePassword(ChangeMap)
                . subscribeOn(Schedulers.io())
                . observeOn(AndroidSchedulers.mainThread());

        SingleObserver <LoginModel> observerChange = new SingleObserver<LoginModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull LoginModel loginModel) {
                Log.d("xCodeR",loginModel.getStatus()+"");
                if ( loginModel.getStatus()==200){
                    binding.progressBar12.setVisibility(View.GONE);
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("phone",  phones);
                    edit.apply();
                    Intent i = new Intent(ForgetPassActivity.this, MainActivity5.class);
                    startActivity(i);
                    finish();
                }

              else   if ( loginModel.getStatus()==400){
                    binding.progressBar12.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassActivity.this, "invalid pass", Toast.LENGTH_SHORT).show();
                }

                else     if ( loginModel.getStatus()==401){
                    binding.progressBar12.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassActivity.this, "pass not matching", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                binding.progressBar12.setVisibility(View.GONE);
                Toast.makeText(ForgetPassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        SingleChangePass.subscribe( observerChange);
    }
    public Boolean isvaled() {



        if ( binding.passeET.getText().toString().isEmpty()) {
            binding.passeET.setError("Enter phone");
            return false;
        }



        if (binding.passET.getText().toString().isEmpty()) {
            binding.passET.setError("Enter pass");
            return false;

        }
        String s =binding.passeET.getText().toString().trim();
        if (s.length() <= 7) {
            binding.passeET.setError("pass not valid");
            return false;
        }


        String ConPasss = (binding.passET.getText().toString().trim());
        if ( ConPasss.length() <= 7) {

            binding.passET.setError("pass not valid");
            return false;
        }

        // if ()




        // if (binding.conPassCraete.getText().toString()!=binding.passCraete.getText().toString()) {
        //     binding.conPassCraete.setError("pass is not equal ConPass ");
        //     return false;
        // }
        // String pass=binding.passCraete.getText().toString().trim();
        // String copass=binding.conPassCraete.getText().toString().trim();
        if (!binding.passeET.getText().toString().trim().equals(binding.passET.getText().toString().trim())) {
            // Toast.makeText(CraeteAccActivity.this,  "pass"+pass, Toast.LENGTH_SHORT).show();
            // Toast.makeText(CraeteAccActivity.this,  "conpass"+copass, Toast.LENGTH_SHORT).show();

            binding.passET.setError("pass is not equal ConPass ");
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
}