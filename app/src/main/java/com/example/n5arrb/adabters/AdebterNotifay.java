package com.example.n5arrb.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n5arrb.R;
import com.example.n5arrb.pojo.NotifayModel;

import java.util.ArrayList;
import java.util.List;

public class AdebterNotifay extends RecyclerView.Adapter<AdebterNotifay.viewholderNotify> {

        List<NotifayModel> modelArrayList=new ArrayList<>();
    Context context;
    NotifayOnClic onClic;

    public AdebterNotifay(Context context, NotifayOnClic onClic) {
        this.context = context;
        this.onClic = onClic;
    }

    public AdebterNotifay() {
    }
    public AdebterNotifay(Context context) {
        this.context = context;
    }

    public List<NotifayModel> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(List<NotifayModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

   @Override
   public int getItemViewType(int position) {
        if(modelArrayList.get(position).getResponse()==1){
            return 1;

        }
        else  if(modelArrayList.get(position).getResponse()==0){
           return 0;

       }
        else  if(modelArrayList.get(position).getResponse()==5){
           return 5;

       }else {
           return 6;
       }

      //  return super.getItemViewType(position)
      // if(modelArrayList.get(position).getResponse()==1){
      //     return 0;
      // }
      // return 1;
   }

    @NonNull
    @Override
    public AdebterNotifay.viewholderNotify onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        if (viewType==1){
            View view = LayoutInflater.from(context).inflate(R.layout.decline, parent, false);
            viewholderNotify viewholder = new viewholderNotify(view);
          //  Toast.makeText(context, viewType, Toast.LENGTH_SHORT).show();
            return viewholder;
        }else if(viewType==0) {
                View view = LayoutInflater.from(context).inflate(R.layout.notifayrow, parent, false);
                viewholderNotify viewholder = new viewholderNotify(view);

                return viewholder;

        }else if(viewType==5) {
            View view = LayoutInflater.from(context).inflate(R.layout.collect1, parent, false);
            viewholderNotify viewholder = new viewholderNotify(view);

            return viewholder;

        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.collect2, parent, false);
            viewholderNotify viewholder = new viewholderNotify(view);

            return viewholder;
        }
        }

    @Override
    public void onBindViewHolder(@NonNull AdebterNotifay.viewholderNotify holder, int position) {

      //  t=position;
 //   //  holder.cardView2.setVisibility(View.GONE);
 //     if (modelArrayList.get(position).getResponse()==1){

 //       //  holder.cardView2.setVisibility(View.GONE);
 //         holder.  cardView22.setVisibility(View.GONE);
 //         holder.  cardView222.setVisibility(View.GONE);
 //         holder.  cardView2.setVisibility(View.GONE);
 //     //  holder.   cardView.setVisibility(View.GONE);

       holder.name_notifay.setText(modelArrayList.get(position).getUserName());
        holder.date_notifay.setText(modelArrayList.get(position).getPaymentDate().replace("T00:00:00",""));
         holder.mony_Notifay.setText(modelArrayList.get(position).getTheAmount()+"");
        holder.describtion_notifay.setText(modelArrayList.get(position).getDetails());

       // holder.declines.setText(modelArrayList.get(position).getDetails());
      //  holder.textView20.setText(modelArrayList.get(position).getDetails());

            holder.declines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClic.onClicNoifay(holder.getAdapterPosition(),modelArrayList.get(position).getId());
                //    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                 // Observable GetTransactions= TransactionsServiceRetrofit.getInstans().
                 //         getApiService().RejectTransaction(modelArrayList.get(position).getId())
                 //         . subscribeOn(Schedulers.io()).
                 //                 observeOn(AndroidSchedulers.mainThread());
                 //  Observer<LoginModel> listObserver= new Observer<LoginModel>() {
                 //      @Override
                 //      public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
                 //      }
//
                 //      @Override
                 //      public void onNext(@io.reactivex.rxjava3.annotations.NonNull LoginModel loginModel) {
//
                 //          Toast.makeText(context, "You declined that Action ", Toast.LENGTH_SHORT).show();
                 //          Toast.makeText(context, "You declined that Action ", Toast.LENGTH_SHORT).show();
                 //         // AdebterNotifay
//
//
                 //      }
//
                 //      @Override
                 //      public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
                 //          Toast.makeText(context, "You declined that Action ", Toast.LENGTH_SHORT).show();
                 //          Toast.makeText(context, "You declined that Action ", Toast.LENGTH_SHORT).show();
                 //      }
//
                 //      @Override
                 //      public void onComplete() {
//
                 //      }
                 //  } ;
                 //  GetTransactions.subscribe(listObserver);
//
              }
          });
//

 //     }else if (modelArrayList.get(position).getResponse()==0) {

 //         holder.  cardView22.setVisibility(View.GONE);
 //       //  holder.  cardView222.setVisibility(View.GONE);
 //         holder.  cardView2.setVisibility(View.GONE);
 //         holder.   cardView.setVisibility(View.GONE);


 //  //      holder.namesss.setVisibility(View.GONE);
 //      //   holder.date_notifay.setText(modelArrayList.get(position).getPaymentDate()+"");
 //  //       holder.name_notifay.setText(modelArrayList.get(position).getUserName());
 //  //      // holder.mony_Notifay.setText(modelArrayList.get(position).getTheAmount()+"");
 //  //       holder.describtion_notifay.setText(modelArrayList.get(position).getDetails());
 //  //       holder.mony_Notifay.setVisibility(View.GONE);
 //  //       holder.describtion_notifay.setVisibility(View.GONE);
 //  //       holder.textView25.setVisibility(View.GONE);
 //  //       holder.decline.setVisibility(View.GONE);
 //  //       holder.cardView2.setVisibility(View.GONE);
 //  //       holder.textView16.setVisibility(View.GONE);
 //  //       holder.description_reg.setText(modelArrayList.get(position).getDetails());
///
 //       // holder.date_notifay.setVisibility(View.GONE);
 //       // holder.mony_Notifay.setVisibility(View.GONE);

 //     }
 //      else if (modelArrayList.get(position).getResponse()==5){
 //       //-  holder.  cardView22.setVisibility(View.GONE);
 //         holder.  cardView222.setVisibility(View.GONE);
 //         holder.  cardView2.setVisibility(View.GONE);
 //         holder.   cardView.setVisibility(View.GONE);
 //        //  holder.cardView2.setVisibility(View.VISIBLE);
 //        //  holder.cardView.setVisibility(View.GONE);
 //      }
 //     else if (modelArrayList.get(position).getResponse()==6){
 //         holder.  cardView22.setVisibility(View.GONE);
 //         holder.  cardView222.setVisibility(View.GONE);
 //       //  holder.  cardView2.setVisibility(View.GONE);
 //         holder.   cardView.setVisibility(View.GONE);

 //        // holder.cardView2.setVisibility(View.VISIBLE);
 //        // holder.cardView.setVisibility(View.GONE);
 //     }
 // //  else if (modelArrayList.get(position).getResponse()==3){
 // //      holder.  cardView22.setVisibility(View.GONE);
 // //      holder.  cardView222.setVisibility(View.GONE);
 // //        holder.  cardView2.setVisibility(View.GONE);
 // //      holder.   cardView.setVisibility(View.GONE);

 // //      // holder.cardView2.setVisibility(View.VISIBLE);
 // //      // holder.cardView.setVisibility(View.GONE);
 // //  }
 // //  else if (modelArrayList.get(position).getResponse()==4){
 // //      holder.  cardView22.setVisibility(View.GONE);
 // //      holder.  cardView222.setVisibility(View.GONE);
 // //       holder.  cardView2.setVisibility(View.GONE);
 // //      holder.   cardView.setVisibility(View.GONE);

 // //      // holder.cardView2.setVisibility(View.VISIBLE);
 // //      // holder.cardView.setVisibility(View.GONE);
 // //  }
 // //  else if (modelArrayList.get(position).getResponse()==2){
 // //      holder.  cardView22.setVisibility(View.GONE);
 // //      holder.  cardView222.setVisibility(View.GONE);
 // //       holder.  cardView2.setVisibility(View.GONE);
 // //      holder.   cardView.setVisibility(View.GONE);

 // //      // holder.cardView2.setVisibility(View.VISIBLE);
 // //      // holder.cardView.setVisibility(View.GONE);
    //  }
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class viewholderNotify extends RecyclerView.ViewHolder   {
        TextView name_notifay,date_notifay,textView16,mony_Notifay,textView25,describtion_notifay,namesss,description_reg;

       // TextView textView20;
        TextView declines;
        CardView cardView2,cardView,cardView22,cardView222;
        ImageView imageView11;
        public viewholderNotify(@NonNull View itemView) {
            super(itemView);

            cardView22  = itemView.findViewById(R.id.cardView22);
            cardView222  = itemView.findViewById(R.id.cardView222);
            cardView2    = itemView.findViewById(R.id.cardView2);
            cardView      = itemView.findViewById(R.id.cardView);
         //   textView20      = itemView.findViewById(R.id.textView20);



            mony_Notifay  = itemView.findViewById(R.id.mony_Notifay);
            name_notifay  = itemView.findViewById(R.id.name_notifay);
            date_notifay  = itemView.findViewById(R.id.date_notifay);
           declines  =  itemView.findViewById(R.id.declines);
            describtion_notifay  = itemView.findViewById(R.id.describtion_notifay);

            namesss  = itemView.findViewById(R.id.namesss);
           // textView20  = itemView.findViewById(R.id.mony_Notifay);

            textView25  = itemView.findViewById(R.id.textView25);
            textView16  = itemView.findViewById(R.id.textView16);






        }


    }



}
