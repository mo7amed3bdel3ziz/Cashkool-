package com.example.n5arrb.UI.authentication.oTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n5arrb.MainActivity;
import com.example.n5arrb.MainActivity2;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.Login;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.OtpModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SendOTPActivity extends AppCompatActivity {
    EditText editTextPhone5,otb;
    String number;
    String id;
    String egyptcode="+2";
    String vrif;
    TextView textView6;
    ProgressBar progressBar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);
        Button button3=findViewById(R.id.button31);
        editTextPhone5=findViewById(R.id.editTextPhone5);
        textView6=findViewById(R.id.textView6);
        progressBar3=findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.GONE);

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(SendOTPActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isvaled() == true) {
                    progressBar3.setVisibility(View.VISIBLE);
                    number = egyptcode + editTextPhone5.getText().toString();
                    Observable otb = TransactionsServiceRetrofit.getInstans().getApiService().sendOtp(number).
                            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    Observer<OtpModel> observer = new Observer<OtpModel>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull OtpModel otpModel) {

                            if (otpModel.isSuccess() == false) {
                                Toast.makeText(SendOTPActivity.this, "Already this phone have acc", Toast.LENGTH_SHORT).show();

                                progressBar3.setVisibility(View.GONE);
                                //false->log in
                                //
                                // Intent i =new Intent(MainActivity.this, MainActivity4.class);
                                // startActivity(i);
                            } else {
                                Toast.makeText(SendOTPActivity.this, "true", Toast.LENGTH_SHORT).show();

                                //true-> vir
                                // SharedPreferences sharedPreferences=getSharedPreferences("user2.getUid()",MODE_PRIVATE);
                                // SharedPreferences.Editor edit=sharedPreferences.edit();
                                // edit.putString("phone",number);
                                // edit.apply();
                                progressBar3.setVisibility(View.GONE);
                                Intent i = new Intent(SendOTPActivity.this, OtpVerifyActivity.class);
                                i.putExtra("phones", number);
                                startActivity(i);
                                finish();
                            }

                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

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


        if ( editTextPhone5.getText().toString().isEmpty()) {
            editTextPhone5.setError("Enter phone");
            return false;
        }

        String s =   editTextPhone5.getText().toString().trim();
        if (s.length() <= 10) {
            editTextPhone5.setError("Phone is not valid");
            return false;
        }
        if (s.length() >= 12) {
            editTextPhone5.setError("Phone is big");
            return false;
        }



        {
            return true;
        }
    }
}