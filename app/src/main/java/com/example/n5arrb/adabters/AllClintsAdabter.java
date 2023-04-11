package com.example.n5arrb.adabters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n5arrb.MainActivity4;
import com.example.n5arrb.UI.allTransaction.AllTransactionForOneClaActivity;
import com.example.n5arrb.R;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsDatabase;
import com.example.n5arrb.pojo.AllClientsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllClintsAdabter extends RecyclerView.Adapter<AllClintsAdabter.holderClint>{

    List<AllClientsModel>clientslist=new ArrayList<>();
    Context context;

    public AllClintsAdabter(Context context) {
        this.context = context;
    }

    public void setClientslist(List<AllClientsModel> clientslist) {
        this.clientslist = clientslist;
    }

    @NonNull
    @Override
    public AllClintsAdabter.holderClint onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customers, parent, false);
        holderClint viewholder = new holderClint(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllClintsAdabter.holderClint holder, int position) {


     //   String phone1=clientslist.get(position).getClientPhone().replace("+2","");

     //  // phone1.
     //    ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(context);
     //   // Log.i("FilterPhone",phones);
     //    contactsDatabase.contactsDao().getNumContacsts(phone1)
     //            .subscribeOn(Schedulers.computation())
     //            .observeOn(AndroidSchedulers.mainThread())
     //            .subscribe(new SingleObserver<String>() {
     //                @Override
     //                public void onSubscribe(@NonNull Disposable d) {

     //                }

     //                @Override
     //                public void onSuccess(@NonNull String s) {



     //                  //  if(s==""){
     //                  //      Log.d("haaaager",s.toString()+"oo");
     //                  //      holder. name69.setText( clientslist.get(position).getClientName());
     //                  //  }else {
     //                    clientslist.get(position).setClientName(s);
     //                    holder. name69.setText( clientslist.get(position).getClientName());
     //                  //  Toast.makeText(AllTransactionForOneClaActivity.this, "Name aho"+s, Toast.LENGTH_SHORT).show();
     //                 //   holder. name69.setText(clientslist.get(position).getClientPhone());
     //              //  }
     //                }

     //                @Override
     //                public void onError(@NonNull Throwable e) {

     //                }
     //            });
   // holder.credit.setText(clientslist.get(position).getNewName());

    ////  clientslist.get(position).getTotalAmount();
      // String cc=clientslist.get(position).getNewName();
      // if ( cc.length()!=0) {

         holder.name69.setText(clientslist.get(position).getNewName());
      // }


        if (clientslist.get(position).getTotalAmount()>0){
           // holder.name69.setText(clientslist.get(position));
           // holder. name69.setText( clientslist.get(position).getNewName());
            holder.phone.setText(clientslist.get(position).getClientPhone());
            holder.monyUp .setText(clientslist.get(position).getTotalAmount()+"");

            holder.imageDown.setVisibility(View.GONE);
            holder.monyDown.setVisibility(View.GONE);
            holder.Debit.setVisibility(View.GONE);
            holder.imageup.setVisibility(View.GONE);



            holder.cc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context, AllTransactionForOneClaActivity.class);
                  //  Intent i=new Intent(context, MainActivity4.class);
                    i.putExtra("phone",clientslist.get(position).getClientPhone());
                    i.putExtra("name",clientslist.get(position).getClientName());


                    context.startActivity(i);

                }
            });

        }else {
          //   holder.name69.setText(clientslist.get(position));
          //  holder. name69.setText( clientslist.get(position).getNewName());
            holder.phone.setText(clientslist.get(position).getClientPhone());
            holder.monyDown .setText(clientslist.get(position).getTotalAmount()+"");
            holder.imageup.setVisibility(View.GONE);
            holder.monyUp.setVisibility(View.GONE);
            holder.credit.setVisibility(View.GONE);
            holder.imageDown.setVisibility(View.GONE);
            holder.cc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent i=new Intent(context, AllTransactionForOneClaActivity.class);
                  //  Intent i=new Intent(context, MainActivity4.class);
                    i.putExtra("phone",clientslist.get(position).getClientPhone());
                    i.putExtra("name",clientslist.get(position).getClientName());

                    context.startActivity(i);

                }
            });
        }

       //holder.name69
       //holder.phone
       //holder.monyUp
       //holder.credit
       //holder.imageDown
       //holder.monyDown

       //holder.cc


    }

    @Override
    public int getItemCount() {
        return clientslist.size();
    }

    public class holderClint extends RecyclerView.ViewHolder {

        TextView name69,phone,monyUp,Debit,credit,monyDown;
        ImageView imageup,imageDown;
        CardView cc;
        public holderClint(@NonNull View itemView) {
            super(itemView);
            name69  = itemView.findViewById(R.id.description_Transaction);
            phone  = itemView.findViewById(R.id.phone);
            monyUp  = itemView.findViewById(R.id.monyUp);
            Debit  = itemView.findViewById(R.id.Debit);
            credit     = itemView.findViewById(R.id.credit);
            imageDown  = itemView.findViewById(R.id.imageDown);
            imageup  = itemView.findViewById(R.id.imageup);
            monyDown   = itemView.findViewById(R.id.monyDown);
            cc        = itemView.findViewById(R.id.cc);


        }
    }
  //  public String getName(String phone){
  //
  //      String a="";
  //
  //      ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(context);
  //     // Log.i("FilterPhone",phones);
  //      contactsDatabase.contactsDao().getNumContacsts(phone)
  //              .subscribeOn(Schedulers.computation())
  //              .observeOn(AndroidSchedulers.mainThread())
  //              .subscribe(new SingleObserver<String>() {
  //                  @Override
  //                  public void onSubscribe(@NonNull Disposable d) {

  //                  }

  //                  @Override
  //                  public void onSuccess(@NonNull String s) {
  //                    //  Toast.makeText(AllTransactionForOneClaActivity.this, "Name aho"+s, Toast.LENGTH_SHORT).show();

  //                      String ss=s;
  //
  //                  }

  //                  @Override
  //                  public void onError(@NonNull Throwable e) {

  //                  }
  //              });
  //
  //  }
}
