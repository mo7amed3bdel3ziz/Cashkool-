package com.example.n5arrb.UI.authentication.oTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.n5arrb.MainActivity2;
import com.example.n5arrb.MainActivity4;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.CraeteAccActivity;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.OtpModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OtpVerifyActivity extends AppCompatActivity {
    Button button2;
    EditText Num_Verify;
    ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        button2=findViewById(R.id.button31);
        Num_Verify=findViewById(R.id.Num_Verify);
        progressBar2=findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);

        Intent i=getIntent();
        String phones=i.getStringExtra("phones");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar2.setVisibility(View.VISIBLE);
             //   if (isvaled() == true) {
                    //Intent i =new Intent(MainActivity2.this, AllTransactionForOneClaActivity.class);
                    String v = Num_Verify.getText().toString();

                    Toast.makeText(OtpVerifyActivity.this, phones, Toast.LENGTH_SHORT).show();
                    Toast.makeText(OtpVerifyActivity.this, v, Toast.LENGTH_SHORT).show();

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
                                progressBar2.setVisibility(View.GONE);
                                Toast.makeText(OtpVerifyActivity.this, "resend or enter code", Toast.LENGTH_SHORT).show();
                            } else {
                                //true-> create
                                Toast.makeText(OtpVerifyActivity.this, " create acc", Toast.LENGTH_SHORT).show();
                                progressBar2.setVisibility(View.GONE);
                                Intent i = new Intent(OtpVerifyActivity.this, CraeteAccActivity.class);
                                i.putExtra("phoneotb", phones);
                                startActivity(i);
                                finish();

                            }


                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            progressBar2.setVisibility(View.GONE);
                            Toast.makeText(OtpVerifyActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

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
  // public Boolean isvaled() {


  //     if ( Num_Verify.getText().toString().isEmpty()) {
  //         Num_Verify.setError("Enter phone");
  //         return false;
  //     }

  //     String s =   Num_Verify.getText().toString().trim();
  //     if (s.length() <= 10) {
  //         Num_Verify.setError("Phone not valid");
  //         return false;
  //     }
  //     if (s.length() >= 12) {
  //         Num_Verify.setError("Phone is big");
  //         return false;
  //     }



  //     {
  //         return true;
  //     }
  // }
}