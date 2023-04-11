package com.example.n5arrb.pojo;

import android.content.Context;

import com.google.firebase.firestore.DocumentReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotifayModel {
    int   Id;
    String   UserPhone;
    String   UserName;
    Double    TheAmount;
   String PaymentDate;
   String   BorrowingDate;
    String   Details;
    String   Transaction_Date;
    int      Left_Day;
    int      Response;
    int      Client_notify;




    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Double getTheAmount() {
        return TheAmount;
    }

    public void setTheAmount(Double theAmount) {
        TheAmount = theAmount;
    }

      public String getPaymentDate() {
          return PaymentDate;
      }

      public void setPaymentDate(String paymentDate) {
          PaymentDate = paymentDate;
      }

      public String getBorrowingDate() {
          return BorrowingDate;
      }

      public void setBorrowingDate(String borrowingDate) {
          BorrowingDate = borrowingDate;
      }

    public int getClient_notify() {
        return Client_notify;
    }

    public void setClient_notify(int client_notify) {
        Client_notify = client_notify;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public int getLeft_Day() {
        return Left_Day;
    }

    public void setLeft_Day(int left_Day) {
        Left_Day = left_Day;
    }

    public int getResponse() {
        return Response;
    }

    public void setResponse(int response) {
        Response = response;
    }

    public String getTransaction_Date() {
        return Transaction_Date;
    }

    public void setTransaction_Date(String transaction_Date) {
        Transaction_Date = transaction_Date;
    }


}
