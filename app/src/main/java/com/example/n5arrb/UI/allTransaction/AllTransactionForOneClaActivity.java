package com.example.n5arrb.UI.allTransaction;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n5arrb.AddActivity;
import com.example.n5arrb.R;
import com.example.n5arrb.ViewModel.AllTransactionViewModel;
import com.example.n5arrb.adabters.AdabterTransaction;
import com.example.n5arrb.adabters.AllClintsAdabter;
import com.example.n5arrb.adabters.RecyclerOnClic;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsDatabase;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsRoom;
import com.example.n5arrb.databinding.ActivityAllTransactionForOneClaBinding;
import com.example.n5arrb.pojo.AllTransaction;
import com.example.n5arrb.pojo.TotalAmount;
import com.example.n5arrb.pojo.UserClientPhones;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllTransactionForOneClaActivity extends AppCompatActivity {

    ActivityAllTransactionForOneClaBinding binding;
    AllTransactionViewModel transactionViewModel;
    String eg="+2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_all_transaction_for_one_cla);
      binding=  DataBindingUtil.setContentView(this, R.layout.activity_all_transaction_for_one_cla);
      binding.progressBar6.setVisibility(View.VISIBLE);
      //  TextView textView35=findViewById(R.id.textView35);
      //  TextView textView33=findViewById(R.id.textView33);
      //  TextView textView37=findViewById(R.id.textView37);
      //  ConstraintLayout constraintLayout2=findViewById(R.id.constraintLayout2);

        Intent i=getIntent();
        String phones=i.getStringExtra("phone");
        String name=i.getStringExtra("name");
        String newNPhone=eg+phones;
        binding.button2.setText(name);



        SharedPreferences sharedPreferenclanguage= getSharedPreferences("language", MODE_PRIVATE);
        SharedPreferences.Editor edits=sharedPreferenclanguage.edit();

        int f=  sharedPreferenclanguage.getInt("test",1 );

        Log.d("lungtest", "لو 1 يبقي عربي لو 2 انجليزى"+"..3.."+f);
        if (f==1){
           // binding.button2.setVisibility(View.GONE);
           // binding.CustomerEn.setVisibility(View.GONE);
            binding.textView28.setVisibility(View.GONE);
           // binding.button32.setVisibility(View.VISIBLE);
            Log.d("lungtest", "عربي");
        }else {
           // binding.button32.setVisibility(View.GONE);
           // binding.CustomerAr.setVisibility(View.GONE);


            binding.textView32.setVisibility(View.GONE);
            Log.d("lungtest", "انجليزى");
        }


        Toast.makeText(AllTransactionForOneClaActivity.this, phones, Toast.LENGTH_SHORT).show();

        AdabterTransaction adabter = new AdabterTransaction(AllTransactionForOneClaActivity.this, new RecyclerOnClic() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClicSheet(int pos, String salary, String discreptin, String date,int cas) {
                 if(cas==1) {

                    // Toast.makeText(AllTransactionForOneClaActivity.this, salary, Toast.LENGTH_SHORT).show();

                     BottomSheetDialog sheet = new BottomSheetDialog(
                             AllTransactionForOneClaActivity.this, R.style.Theme_Design_BottomSheetDialog
                     );
                     View btnSheet = LayoutInflater.from(getApplicationContext())
                             .inflate(R.layout.layuot_btn_sheet_d,
                                     (LinearLayout) findViewById(R.id.sheet));

                     TextView name1 = btnSheet.findViewById(R.id.textView35);
                     name1.setText(name);


                     TextView salarys = btnSheet.findViewById(R.id.textView33);
                     salarys.setText(salary);
                     // textView33.setText(salary);
                     // textView37.setText(discreptin);
                     TextView discreptin1 = btnSheet.findViewById(R.id.textView37);
                     discreptin1.setText(discreptin);

                     btnSheet.findViewById(R.id.constraintLayout2)


                             .setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     String phoneNumberWithCountryCode = phones;
                                     String message = "Hallo "+name+"!!  From Kashcool ..... \n" +"\n"+
                                             "your friend :\n"+phones+"\n"+" Ask you.!!\n why you Recording on  "+salary+"  for  "+discreptin;

                                     startActivity(
                                             new Intent(Intent.ACTION_VIEW,
                                                     Uri.parse(
                                                             String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                                                     )
                                             )
                                     );
                                 }
                             });


                     sheet.setContentView(btnSheet);
                     sheet.show();
                 }else {
                     BottomSheetDialog sheet = new BottomSheetDialog(
                             AllTransactionForOneClaActivity.this, R.style.Theme_Design_BottomSheetDialog
                     );
                     View btnSheet = LayoutInflater.from(getApplicationContext())
                             .inflate(R.layout.layuot_btn_sheet_d,
                                     (LinearLayout) findViewById(R.id.sheet));

                     TextView name1 = btnSheet.findViewById(R.id.textView35);
                     name1.setText("me");


                     TextView salarys = btnSheet.findViewById(R.id.textView33);
                     salarys.setText(salary);
                     // textView33.setText(salary);
                     // textView37.setText(discreptin);
                     TextView discreptin1 = btnSheet.findViewById(R.id.textView37);
                     discreptin1.setText(discreptin);

                     btnSheet.findViewById(R.id.constraintLayout2)


                             .setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     String phoneNumberWithCountryCode = phones;
                                     String message = "Hallo "+name+"!!  From Kashcool ..... \n" +"\n"+
                                             "your friend :\n"+phones+"\n"+"Recording on  "+salary+"  for  "+discreptin;


                                     startActivity(
                                             new Intent(Intent.ACTION_VIEW,
                                                     Uri.parse(
                                                             String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                                                     )
                                             )
                                     );
                                 }
                             });


                     sheet.setContentView(btnSheet);
                     sheet.show();
                 }

            }
        });
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      binding.recTransactions.setLayoutManager(linearLayoutManager);

       transactionViewModel=new ViewModelProvider(this).get(AllTransactionViewModel.class);

        SharedPreferences sharedPreferences= getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        String pf=  sharedPreferences.getString("phone","");


       UserClientPhones s=new UserClientPhones(pf,phones);
       transactionViewModel.getTransactions(s);
       transactionViewModel.getAllTransactionliveDataVm().observe(this, new Observer<List<AllTransaction>>() {
           @Override
           public void onChanged(List<AllTransaction> allTransactions) {
               binding.progressBar6.setVisibility(View.GONE);
               adabter.setArrayList(allTransactions);
               binding.recTransactions.setAdapter(adabter);
           }
       });

       transactionViewModel.getTotalLiveDataVm().observe(this, new Observer<TotalAmount>() {
           @Override
           public void onChanged(TotalAmount totalAmount) {
               binding.textView13.setText(totalAmount.getNetbalance()+"");
               binding.textView9.setText(  totalAmount.getTotalDepits() +"");
               binding.textView10.setText(  totalAmount.getTotalCredits()+"");

           }
       });


       String f2=phones.replace("+2","");
      binding.floatingActionadd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i =new Intent(AllTransactionForOneClaActivity.this, AddActivity.class);

              i.putExtra("num",f2);
             // i.putExtra("num",name);
             // Toast.makeText(AllTransactionForOneClaActivity.this, f2, Toast.LENGTH_SHORT).show();
              i.putExtra("name",name);
              i.putExtra("state","1");

              startActivity(i);

          }
      });

      binding.floatingActionDebitMe.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i =new Intent(AllTransactionForOneClaActivity.this, AddActivity.class);

              i.putExtra("num",f2);
              // i.putExtra("num",name);
              // Toast.makeText(AllTransactionForOneClaActivity.this, f2, Toast.LENGTH_SHORT).show();
              i.putExtra("name",name);
              i.putExtra("state","2");

              startActivity(i);

          }
      });

  //   binding.whatsApp.setOnClickListener(new View.OnClickListener() {
  //       @Override
  //       public void onClick(View view) {
  //           BottomSheetDialog sheet=new  BottomSheetDialog(
  //                   AllTransactionForOneClaActivity.this,R.style.Theme_Design_BottomSheetDialog
  //           );
  //           View btnSheet= LayoutInflater.from(getApplicationContext())
  //                   .inflate(R.layout.layuot_btn_sheet_d,
  //                           (LinearLayout)findViewById(R.id.sheet));

  //           sheet.setContentView(btnSheet);
  //           sheet.show();
  //           Toast.makeText(AllTransactionForOneClaActivity.this, "dd", Toast.LENGTH_SHORT).show();
  //  //   String phoneNumberWithCountryCode = phones;
  //  //   String message = "Hallo!!  From Kashcool " +
  //  //           "you have received an invitation to download Kashcool";

  //  //   startActivity(
  //  //           new Intent(Intent.ACTION_VIEW,
  //  //                   Uri.parse(
  //  //                           String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
  //  //                   )
  //  //           )
  //  //   );
  //       }
  //   });

    }


}