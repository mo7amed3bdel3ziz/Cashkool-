package com.example.n5arrb.contactesAccess;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n5arrb.AddActivity;
import com.example.n5arrb.R;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsRoom;

import java.util.ArrayList;
import java.util.List;

public class AdabteContact extends RecyclerView.Adapter<AdabteContact.HolderContant> {

    List<ContactsRoom> contactUserslist=new ArrayList<>();
    Context context;



    public AdabteContact(Context context) {
        this.context = context;
    }

    public void setContactUserslist(List<ContactsRoom> contactUserslist) {
        this.contactUserslist = contactUserslist;
    }



    public void filterList(ArrayList<ContactsRoom> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        contactUserslist= filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AdabteContact.HolderContant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customers, parent, false);
        HolderContant viewholder = new HolderContant(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdabteContact.HolderContant holder, int position) {

        //    ContactsRoom model=contactUserslist.get(position);
        holder.name.setText(contactUserslist.get(position).getName());
        //holder.name.setText(contactUserslist.get(position).getNumber());
        holder.number.setText(contactUserslist.get(position).getNumber());
        holder.c.setVisibility(View.GONE);
        holder.s.setVisibility(View.GONE);


        holder.  monyDown.setVisibility(View.GONE);
        holder.          credit.setVisibility(View.GONE);
        holder.  Debit.setVisibility(View.GONE);
        holder.          monyUp.setVisibility(View.GONE);

        holder.cc.setOnClickListener(new View.OnClickListener() {
            String names= contactUserslist.get(position).getName();
            String nums=contactUserslist.get(position).getNumber();
            @Override
            public void onClick(View view) {


                Log.d("Kshkool",contactUserslist.get(position).toString());
                Intent i =new Intent(context, AddActivity.class);
                i.putExtra("name",names);
                Log.d("o7gsxhciouhcs7y79y98s0u",nums);
                i.putExtra("num",nums);
                i.putExtra("state","1");


                context. startActivity(i);


            }
        });


    }

    @Override
    public int getItemCount() {
        return contactUserslist.size();
    }

    public class HolderContant extends RecyclerView.ViewHolder {
        TextView name,number,monyDown,credit,Debit,monyUp;
        ImageView c,s;
        //  ConstraintLayout rows;

        CardView cc;
        public HolderContant(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.description_Transaction);
            number = itemView.findViewById(R.id.phone);
            s= itemView.findViewById(R.id.imageDown);
            c= itemView.findViewById(R.id.imageup);
            cc= itemView.findViewById(R.id.cc);

            monyDown= itemView.findViewById(R.id.monyDown);
            credit  = itemView.findViewById(R.id.credit);
            Debit   = itemView.findViewById(R.id.Debit);
            monyUp  = itemView.findViewById(R.id.monyUp);
            //   rows = itemView.findViewById(R.id.rows);


        }
    }
}