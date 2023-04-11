package com.example.n5arrb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.n5arrb.UI.authentication.Login;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.databinding.ActivityMain2Binding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.OtpModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity2 extends AppCompatActivity {
    Button button2;
    private FirebaseAuth mAuth;
    EditText Num_Verify;
    ActivityMain2Binding binding;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);


        button2=findViewById(R.id.button31);
        Num_Verify=findViewById(R.id.Num_Verify);
        Intent i=getIntent();
        String phones=i.getStringExtra("phones");
        sendOtb();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String s= sendOtb();
                Toast.makeText(MainActivity2.this, s, Toast.LENGTH_SHORT).show();

                //Intent i =new Intent(MainActivity2.this, AllTransactionForOneClaActivity.class);
          //String v=  Num_Verify.getText().toString();

          //  Toast.makeText(MainActivity2.this, phones, Toast.LENGTH_SHORT).show();
          //  Toast.makeText(MainActivity2.this,v, Toast.LENGTH_SHORT).show();

          //  HashMap<Object,Object> map=new HashMap<>();

          //  map.put("PhoneNumber",phones);
          //  map.put("Code",v);
          //  Observable otb= TransactionsServiceRetrofit.getInstans().getApiService().sendVerifyOtp(map).
          //          subscribeOn(Schedulers.io()) . observeOn(AndroidSchedulers.mainThread());

          //  Observer<OtpModel> observer= new Observer<OtpModel>() {
          //      @Override
          //      public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

          //      }

          //      @Override
          //      public void onNext(@io.reactivex.rxjava3.annotations.NonNull OtpModel otpModel) {

          //          if (otpModel.isSuccess()==false){
          //              //false ->re enter code (resend or enter code)
          //              Toast.makeText(MainActivity2.this, "resend or enter code", Toast.LENGTH_SHORT).show();
          //          }else {
          //              //true-> create
          //              Toast.makeText(MainActivity2.this, " create acc", Toast.LENGTH_SHORT).show();

          //              Intent i =new Intent(MainActivity2.this, MainActivity4.class);
          //              i.putExtra("phoneotb",phones);
          //              startActivity(i);

          //          }


          //      }

          //      @Override
          //      public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

          //      }

          //      @Override
          //      public void onComplete() {

          //      }
          //  };
              //  otb.subscribe(observer);
               // Intent x =new Intent(MainActivity2.this, MainActivity5.class);
               // startActivity(x);
            }
        });

    }
    private  void sendVerificationcode(){

        mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                      //  .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                // This callback will be invoked in two situations:
                                // 1 - Instant verification. In some cases the phone number can be instantly
                                //     verified without needing to send or enter a verification code.
                                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                                //     detect the incoming verification SMS and perform verification without
                                //     user action.
                                //  Log.d(TAG, "onVerificationCompleted:" + credential);

                                signInWithPhoneAuthCredential(credential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.
                                // Log.w(TAG, "onVerificationFailed", e);

                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                }

                                // Show a message and update the UI
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                               // MainActivity.this.id=verificationId;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");

                         //   startActivity(new Intent(MainActivity.this,MainActivity2.class));
                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            //      Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    private String sendOtb(){
        String number ;
        binding.NumVerify1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    binding.NumVerify2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.NumVerify2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    binding.NumVerify3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.NumVerify3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    binding.NumVerify4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.NumVerify4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    binding.NumVerify5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.NumVerify5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    binding.NumVerify6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.NumVerify6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    binding.NumVerify6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return   binding.NumVerify1.getText().toString()+ binding.NumVerify2.getText().toString()+ binding.NumVerify3.getText().toString()+binding.NumVerify4.getText().toString()+ binding.NumVerify5.getText().toString()+ binding.NumVerify6.getText().toString();

    }
}