package com.example.n5arrb.UI.mainFragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.n5arrb.ViewModel.HomeViewModel;
import com.example.n5arrb.adabters.AllClintsAdabter;
import com.example.n5arrb.contactesAccess.ContactsActivity;
import com.example.n5arrb.databinding.FragmentHomeBinding;
import com.example.n5arrb.pojo.AllClientsModel;
import com.example.n5arrb.pojo.TotalAmountModel;

import java.util.List;
import java.util.Locale;


public class homeFragment extends Fragment {



    FragmentHomeBinding binding;
  // HomeViewModel  viewModels=new ViewModelProvider(getActivity()).get(HomeViewModel.class);
    HomeViewModel  viewModels;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=  FragmentHomeBinding .inflate(getLayoutInflater(),container,false);
        binding.progressBar11.setVisibility(View.VISIBLE);
       viewModels=new ViewModelProvider(getActivity()).get(HomeViewModel.class);

  //     SharedPreferences sharedPreferenclanguage= getActivity().getApplicationContext().getSharedPreferences("language", MODE_PRIVATE);
  //     SharedPreferences.Editor edits=sharedPreferenclanguage.edit();
  //     String pfs=  sharedPreferenclanguage.getString("lang",null);
  // //    String tests=  sharedPreferenclanguage.getString("test",null);
  //     Log.d("lungtest","تاكيد او ما بيدخل 1 مش فاضي"+"...."+ pfs);
  //    // Log.d("lungtest","تاكيد او ما بيدخل 1 مش فاضي"+"...."+ tests);

  //     if(pfs==null){
  //        SharedPreferences sharedPreferenclanguageg =getActivity().getApplicationContext().getSharedPreferences("language", MODE_PRIVATE);
  //        SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();

  //        editsg.putString("lang","en");
  //       // editsg.putString("test","2");
  //        editsg.apply();
  //         Log.d("lungtest", "2ده لو فاض بيخش هنا"+"...."+pfs);
  //       // setLocale("en");
  //     }else {
  //         setLocale( pfs);
  //      int f=  sharedPreferenclanguage.getInt("test",1 );

  //         Log.d("lungtest", "لو 1 يبقي عربي لو 2 انجليزى"+"..3.."+f);
  //         if (f==1){
  //             binding.button2.setVisibility(View.GONE);
  //             binding.CustomerEn.setVisibility(View.GONE);
  //             binding.button32.setVisibility(View.VISIBLE);
  //             Log.d("lungtest", "عربي");
  //         }else {
  //             binding.button32.setVisibility(View.GONE);
  //             binding.CustomerAr.setVisibility(View.GONE);
  //             binding.button2.setVisibility(View.VISIBLE);
  //             Log.d("lungtest", "انجليزى");
  //         }


  //     }

     //  binding.button2.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View view) {
     //          SharedPreferences sharedPreferenclanguage =getActivity().getApplicationContext(). getSharedPreferences("language", MODE_PRIVATE);
     //          SharedPreferences.Editor edits = sharedPreferenclanguage.edit();
     //          edits.putString("lang","ar");
     //          edits.putInt("test",1);
     //          edits.apply();

     //       //   setLocale("ar");
     //         // binding.button2.setVisibility(View.GONE);
     //         // binding.button32.setVisibility(View.VISIBLE);
     //          Intent i =new Intent(getActivity(), MainActivity5.class);
     //          startActivity(i);
     //          getActivity().onBackPressed();
     //      }
     //  });

        binding.button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferenclanguage =getActivity().getApplicationContext(). getSharedPreferences("language", MODE_PRIVATE);
                SharedPreferences.Editor edits = sharedPreferenclanguage.edit();
                edits.putString("lang","en");
                edits.putInt("test",2);
                edits.apply();
              //  setLocale("en");
              // binding.button32.setVisibility(View.GONE);
              // binding.button2.setVisibility(View.VISIBLE);
                Intent i =new Intent(getActivity(), MainActivity5.class);
                startActivity(i);
                getActivity().onBackPressed();
            }
        });

     //   setLocale("ar");

       binding.floatingActionadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             Intent i =new Intent(getActivity(), ContactsActivity.class);
             startActivity(i);

           }
       });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        AllClintsAdabter adabter=new AllClintsAdabter(getActivity());
        binding.RecyclerViewCustomer.setLayoutManager(linearLayoutManager);

        SharedPreferences sharedPreferences= getActivity().getApplicationContext().getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();


      String pf=  sharedPreferences.getString("phone","");
      ///  Toast.makeText(getActivity(), pf, Toast.LENGTH_SHORT).show();
      Log.d("hend",pf+"رقم");
       // Toast.makeText(getActivity(), pf, Toast.LENGTH_SHORT).show();
         // viewModels.getallClint("+201155447106");
         // viewModels.getTotalAmount("+201155447106");
        // viewModels.getTotalAmount("+201000399179");
        // viewModels.getallClint("+201000399179");

     //viewModels.getTotalAmount("+201155447106");
    // viewModels.getallClint("+201155447106");
     viewModels.getallClint(pf,getActivity().getApplicationContext());

       // viewModels.getTotalAmount("+201000599179");
       // viewModels.getallClint("+201000599179");

        //viewModels.getTotalAmounts("+201155447106");
        viewModels.getTotalAmounts(pf);

         viewModels.getGetTotalAmountLivedata().observe(getActivity(), new Observer<TotalAmountModel>() {
             @Override
             public void onChanged(TotalAmountModel totalAmountModel) {
                 binding.textView13.setText( totalAmountModel.getBalance());

                 binding.textView9.setText( totalAmountModel.getCredit());
                 binding.textView10.setText(totalAmountModel.getDibet());
                // binding.progressBar11.setVisibility(View.GONE);
             }
         });




         viewModels.getGetallClintsLive().observe(getActivity(), new Observer<List<AllClientsModel>>() {
             @Override
             public void onChanged(List<AllClientsModel> allClientsModels) {

               //  if(allClientsModels !=null){



                 //    Log.d("hend",allClientsModels.get(0).getCredit()+"");
                     adabter.setClientslist(allClientsModels);
                     binding.RecyclerViewCustomer.setAdapter(adabter);
                     binding.progressBar11.setVisibility(View.GONE);
               ///  }else {
                     //binding.progressBar11.setVisibility(View.GONE);
                     Log.d("hend","");
             // Toast.makeText(getContext().getApplicationContext(), " conaction so bad", Toast.LENGTH_SHORT).show();
               //  }

                 //1189


               //  binding.textView10.setText(allClientsModels.get(0).getCredit()+"");
               //  binding.textView9.setText(allClientsModels.get(0).getDepit()+"");

               //  Toast.makeText(getActivity(),allClientsModels.get(1).getNewName(), Toast.LENGTH_SHORT).show();
             }
         });


       // if (allClientsModels.isEmpty()){
       //     binding.progressBar11.setVisibility(View.GONE);}
        return   binding.getRoot();

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