package com.example.n5arrb.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n5arrb.R;
import com.example.n5arrb.pojo.AllTransaction;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdabterTransaction extends RecyclerView.Adapter<AdabterTransaction.viewholder> {
    Context context;
   List<AllTransaction> arrayList=new ArrayList<>();
   RecyclerOnClic onClic;

    public AdabterTransaction(Context context, RecyclerOnClic onClic) {
        this.context = context;
        this.onClic = onClic;
    }

    public AdabterTransaction(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<AllTransaction> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<AllTransaction> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contactrow, parent, false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {



        //Credit
        if (arrayList.get(position).getCredit_Depit()==0){

            holder.description_Transaction.setText(arrayList.get(position).getDetails());
            holder. mony_one_Action.setText(arrayList.get(position).getTheAmount()+"");

            Toast.makeText(context, "dsdsdsds", Toast.LENGTH_SHORT).show();
          //  holder.date_pay.setText(arrayList.get(position).getBorrowingDate()+"");
            holder.date_pay.setText(arrayList.get(position).getPaymentDate().replace("T00:00:00",""));

            holder.CostID.setText("فلوس عليا");

            holder.mony_up.setVisibility(View.GONE);
            holder.imageup.setVisibility(View.GONE);
            holder.rows.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClic.onClicSheet(holder.getAdapterPosition()
                            ,arrayList.get(position).getTheAmount()+"",arrayList.get(position).
                                    getDetails(),arrayList.get(position).getPaymentDate().replace("T00:00:00","") ,1);
                 // BottomSheetDialog sheet=new  BottomSheetDialog(
                 //         context,R.style.Theme_Design_BottomSheetDialog
                 // );
                 // View btnSheet=LayoutInflater.from(getContext())
                 //         .inflate(R.layout.layuot_btn_sheet_d,
                 //                 (LinearLayout)finalize(R.id.sheet);)

                }
            });
        }else {
            holder.description_Transaction.setText(arrayList.get(position).getDetails());
            holder. mony_up.setText(arrayList.get(position).getTheAmount()+"");

            holder.CostID.setText("فلوس ليا");

            holder.image_d.setVisibility(View.GONE);
          //  holder.date_pay.setText(arrayList.get(position).getBorrowingDate()+"");
            holder.date_pay.setText(arrayList.get(position).getPaymentDate().replace("T00:00:00",""));
          holder.  mony_one_Action.setVisibility(View.GONE);

            holder.imagedp.setVisibility(View.GONE);
            holder.rows.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onClic.onClicSheet(holder.getAdapterPosition()
                            ,arrayList.get(position).getTheAmount()+"",arrayList.get(position).
                                    getDetails(),arrayList.get(position).getPaymentDate() ,2);
                }
            });
        }
      //  holder.description_Transaction.setText(arrayList.get(position).getDetails());
      //  holder. mony_one_Action.setText("$ "+arrayList.get(position).getTheAmount());
      //  holder.date_pay.setText(arrayList.get(position).getPaymentDate()+"");
      //  holder.imageup.setVisibility(View.GONE);
       // holder.cc.setOnClickListener(new View.OnClickListener() {
    //     @Override
    //     public void onClick(View view) {
    //         Intent i =new Intent(context,AddTransactionTOne.class);
    //         i.putExtra("name",arrayList.get(position).getName());
    //         i.putExtra("Senderphone",arrayList.get(position).getSenderphone());
    //         i.putExtra("receiverId",arrayList.get(position).receiverId);
    //        context. startActivity(i);





    //     }
    // });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView description_Transaction,date_pay,mony_one_Action,textView19,CostID;
        ImageView imageup,imagedp;
        CardView  cc;
        CardView  rows;
        TextView mony_up;
        CircleImageView image_d;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            description_Transaction = itemView.findViewById(R.id.description_Transaction);
            mony_one_Action  = itemView.findViewById(R.id.mony_one_Action);
            mony_up  = itemView.findViewById(R.id.mony_up);
            date_pay = itemView.findViewById(R.id.date_pay);
            CostID = itemView.findViewById(R.id.CostID);
            rows = itemView.findViewById(R.id.rows);
            textView19  = itemView.findViewById(R.id.Debit);
            cc = itemView.findViewById(R.id.cc);
            imageup = itemView.findViewById(R.id.imageup);
            imagedp = itemView.findViewById(R.id.imagedp);
            image_d = itemView.findViewById(R.id.image_d);
        }
    }
}
