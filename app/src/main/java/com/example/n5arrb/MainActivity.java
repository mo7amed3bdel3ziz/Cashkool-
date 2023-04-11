package com.example.n5arrb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 import android.widget.Toast;


import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.auth.OtpModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
  //https://www.youtube.com/watch?v=GW9tzXTtrL8
    Button button;
    String URLs  = "https://worldclockapi.com/api/json/est/now";
    String URLss = "https://api.countapi.xyz/hit/namespace/key";
    String URL   = "https://www.timeapi.io/api/Time/current/coordinate?latitude=38.9&longitude=-77.03";
    private FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
   // RequestQueue queue;
    EditText editTextPhone5,otb;
 // @RequiresApi(api = Build.VERSION_CODES.O)
    PhoneAuthProvider.ForceResendingToken tokens;
    String number;
    String id;
 // PhoneAuthProvider.@NonNull ForceResendingToken tokens;
    String egyptcode="+2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        Button button3=findViewById(R.id.button31);
        editTextPhone5=findViewById(R.id.editTextPhone5);
      //  otb=findViewById(R.id.otb);


       button3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           //    sendVerificationcode(number);
             number= egyptcode+ editTextPhone5.getText().toString();
               Observable otb= TransactionsServiceRetrofit.getInstans().getApiService().sendOtp(number).
               subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
               Observer <OtpModel> observer= new Observer<OtpModel>() {
                   @Override
                   public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@io.reactivex.rxjava3.annotations.NonNull OtpModel otpModel) {

                       if (otpModel.isSuccess()==false){
                           Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();

                         // false->log in
                         // Intent i =new Intent(MainActivity.this, MainActivity4.class);
                         // startActivity(i);

                       }else {
                           Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();

                         // true-> vir
                         // SharedPreferences sharedPreferences=getSharedPreferences("user2.getUid()",MODE_PRIVATE);
                         // SharedPreferences.Editor edit=sharedPreferences.edit();
                         // edit.putString("phone",number);
                         // edit.apply();

                           Intent i =new Intent(MainActivity.this, MainActivity2.class);
                           i.putExtra("phones",number);
                           startActivity(i);
                       }
                   }

                   @Override
                   public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                       Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onComplete() {

                   }

               };
               otb.subscribe(observer);
             //  Intent i =new Intent(MainActivity.this, MainActivity4.class);
             //  startActivity(i);

       //      Toast.makeText(MainActivity.this, number, Toast.LENGTH_SHORT).show();
       //   SharedPreferences sharedPreferences=getSharedPreferences("number",MODE_PRIVATE);
       //   SharedPreferences.Editor edit=sharedPreferences.edit();
       //   edit.putString("phone",number);
       //   edit.apply();
       //   Phone f=new Phone();
       //   f.setPhones(number);

          //   Intent i =new Intent(MainActivity.this, MainActivity4.class);
          //  startActivity(i);
            //   PhoneAuthCredential credential=PhoneAuthProvider.getCredential(id,editTextPhone5.getText().toString().replace(
            //           "",""
            //   ));
              // vrif=egyptcode+editTextPhone5.getText().toString();
              // Toast.makeText(MainActivity.this, vrif, Toast.LENGTH_SHORT).show();
              //Intent i=   new Intent(MainActivity.this,MainActivity2.class);
            //  sendVerificationcode(vrif);
             //startActivity();
           //PhoneAuthCredential credential=PhoneAuthProvider.getCredential(id,otb.toString()
           //        .replace("",""));


           //signInWithPhoneAuthCredential(credential);
        // PhoneAuthProvider.getInstance().verifyPhoneNumber(
        //         number,60,TimeUnit.SECONDS,
        //         MainActivity.this,new  PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        //             @Override
        //             public void onVerificationCompleted(@androidx.annotation.NonNull PhoneAuthCredential phoneAuthCredential) {

        //                 Toast.makeText(MainActivity.this,"1", Toast.LENGTH_SHORT).show();
        //             }

        //             @Override
        //             public void onVerificationFailed(@androidx.annotation.NonNull FirebaseException e) {

        //                 Log.d("yoyo", String.valueOf(e));
        //                 Toast.makeText(MainActivity.this,"2", Toast.LENGTH_SHORT).show();
        //             } @Override
        //             public void onCodeSent(@NonNull String verificationId,
        //                                    @NonNull PhoneAuthProvider.ForceResendingToken token) {
        //                 Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
        //                 Intent i =new Intent(MainActivity.this,MainActivity2.class);
        //                 startActivity(i);
        //               // id=verificationId;
        //               // tokens=token;
        //                 //  MainActivity.this.id=verificationId;
        //             }
        //         }
        // );

           }
       });



      //      tv = findViewById(R.id.tv);
      //      button = findViewById(R.id.button);
      //      queue = Volley.newRequestQueue(MainActivity.this);
      //      button.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
            // StringRequest request = new StringRequest(Request.Method.GET, URLss, new com.android.volley.Response.Listener<String>() {
            //     @Override
            //     public void onResponse(String response) {
            //         tv.setText(response.toString()+"");

            //     }

            // } ,new com.android.volley.Response.ErrorListener() {
            //     @Override
            //     public void onErrorResponse(VolleyError error) {

            //     }
            // });
            // queue.add(request);


       //     queue = Volley.newRequestQueue(MainActivity.this);
       //      JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLss,
       //              null, new Response.Listener<JSONObject>() {
       //          @Override
       //          public void onResponse(JSONObject response) {

       //              try {
       //                  String count=   response.getString("value");
       //                  tv.setText(count);
       //              } catch (JSONException e) {
       //                  e.printStackTrace();
       //              }
       //          }
       //      } ,new Response.ErrorListener() {
       //    @Override
       //    public void onErrorResponse(VolleyError error) {

       //    }
       //});
       //               queue.add(request);


      //                 Retrofit retrofit=new Retrofit.Builder()
      //                        .baseUrl("https://api.countapi.xyz/")
      //                         .addConverterFactory(GsonConverterFactory.create())
      //                         .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      //                         .build();
      //               interfac apiInterfac=retrofit.create(interfac.class);

      //               Observable observable=apiInterfac.getpost(). subscribeOn(Schedulers.io())
      //                       . observeOn(AndroidSchedulers.mainThread());
      //               Observer<value> o=new Observer<value>() {
      //                   @Override
      //                   public void onSubscribe(@NonNull Disposable d) {

      //                   }

      //                   @Override
      //                   public void onNext(@NonNull value value) {
      //                       tv.setText(value.getValue());
      //                   }

      //                   @Override
      //                   public void onError(@NonNull Throwable e) {

      //                   }

      //                   @Override
      //                   public void onComplete() {

      //                   }
      //               };
      //               observable.subscribe(o);
      //               //   Call<value> call=apiInterfac.getpost();
      //               // call.enqueue(new Callback<value>() {
      //               //     @Override
      //               //     public void onResponse(Call<value> call, retrofit2.Response<value> response) {

      //               //         tv.setText( response.body().getValue());

      //               //     }

      //               //     @Override
      //               //     public void onFailure(Call<value> call, Throwable t) {

      //               //     }
      //               // });

      //               //   Retrofit retrofit=new Retrofit.Builder()
      //               //          .baseUrl("https://api.countapi.xyz/")
      //               //           .addConverterFactory(GsonConverterFactory.create())
      //               //          // .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      //               //           .build();
      //               //   interfac apiInterfac=retrofit.create(interfac.class);
      //               //   Call<value> call =apiInterfac.getGenericJSON();
      //               //   call.enqueue(new Callback<value>() {
      //               //       @Override
      //               //       public void onResponse(Call<value> call, Response<value> response) {
      //               //           tv.setText(response.body().getValue());
      //               //       }

      //               //       @Override
      //               //       public void onFailure(Call<value> call, Throwable throwable) {

      //               //       }
      //               //   });

    }

    private  void sendVerificationcode(String num){


        mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(num)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(MainActivity.this)                 // Activity (for callback binding)
                        .setCallbacks(  mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                // This callback will be invoked in two situations:
                                // 1 - Instant verification. In some cases the phone number can be instantly
                                //     verified without needing to send or enter a verification code.
                                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                                //     detect the incoming verification SMS and perform verification without
                                //     user action.
                                //  Log.d(TAG, "onVerificationCompleted:" + credential);

                                // signInWithPhoneAuthCredential(credential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.
                                // Log.w(TAG, "onVerificationFailed", e);

                                // Show a message and update the UI
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {


                                id=verificationId;
                                tokens=token;
                                //  MainActivity.this.id=verificationId;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth = FirebaseAuth.getInstance();
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

    public String convertToArabic(int value)
    {
        String newValue = (((((((((((value+"")
                .replaceAll("1", "١")).replaceAll("2", "٢"))
                .replaceAll("3", "٣")).replaceAll("4", "٤"))
                .replaceAll("5", "٥")).replaceAll("6", "٦"))
                .replaceAll("7", "٧")).replaceAll("8", "٨"))
                .replaceAll("9", "٩")).replaceAll("0", "٠"));
        return newValue;
    }

}