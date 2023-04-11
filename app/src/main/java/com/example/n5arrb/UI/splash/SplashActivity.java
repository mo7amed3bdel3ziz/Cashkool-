package com.example.n5arrb.UI.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.n5arrb.MainActivity2;
import com.example.n5arrb.MainActivity3;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.authentication.CraeteAccActivity;
import com.example.n5arrb.UI.authentication.Login;
import com.example.n5arrb.UI.authentication.forgetPassword.ForgetPassActivity;
import com.example.n5arrb.UI.authentication.oTP.SendOTPActivity;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.ViewModel.HomeViewModel;
import com.example.n5arrb.ViewModel.NotifayViewModel;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // Intent i=new Intent(SplashActivity.this, MainActivity2.class);
       // startActivity(i);
       Thread background = new Thread() {
           public void run() {
               try {
                   // Thread will sleep for 5 seconds
                   sleep(1*1000);

                 //  SharedPreferences sharedPreferenclanguage = getSharedPreferences("language", MODE_PRIVATE);
                 //  SharedPreferences.Editor edits = sharedPreferenclanguage.edit();
                 //  edits.putString("lang","ar");
                 //  edits.apply();
//
                 //  setLocale("d");
               //    SharedPreferences sharedPreferenceslaun= getSharedPreferences("language", MODE_PRIVATE);
               //    SharedPreferences.Editor editl=sharedPreferenceslaun.edit();
               //    String pfl=  sharedPreferenceslaun.getString("phone","ar");
               //    setLocale(pfl);



                   SharedPreferences sharedPreferences= getSharedPreferences("Login", MODE_PRIVATE);
                   SharedPreferences.Editor edit=sharedPreferences.edit();
                   String pf=  sharedPreferences.getString("phone",null);

                   if(pf==null){
                       Intent i=new Intent(getBaseContext(), SendOTPActivity.class);
                       startActivity(i);
                       finish();
                   }else {
                     // NotifayViewModel     viewModel=new ViewModelProvider(SplashActivity.this).get(NotifayViewModel.class);

                     // HomeViewModel   viewModels=new ViewModelProvider(SplashActivity.this).get(HomeViewModel.class);
                     // viewModels.getallClint(pf,SplashActivity.this);
                     // viewModel.getNotifay(pf);

                       Intent i=new Intent(getBaseContext(), MainActivity5.class);
                       startActivity(i);
                       finish();
                   }
                   // After 5 seconds redirect to another intent


                   //Remove activity

               } catch (Exception e) {
               }
           }
       };
       // start thread
       background.start();
    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        Intent refresh = new Intent(SplashActivity.this, MainActivity5.class);
        startActivity(refresh);
    }
}