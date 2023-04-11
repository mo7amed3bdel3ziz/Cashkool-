package com.example.n5arrb.pojo;

import java.util.Date;

public class AddTransactions {

   Double TheAmount;
   Date PaymentDate;
   Date  BorrowingDate;
   String Details;
   String ClientName;
   String    ClientPhone;
   String    UserPhone;

    public AddTransactions(Double theAmount,Date paymentDate,Date borrowingDate,
                           String details, String clientName, String clientPhone,
                           String userPhone) {
        TheAmount = theAmount;
        PaymentDate = paymentDate;
        BorrowingDate = borrowingDate;
        Details = details;
        ClientName = clientName;
        ClientPhone = clientPhone;
        UserPhone = userPhone;
    }

    public Double getTheAmount() {
        return TheAmount;
    }

    public void setTheAmount(Double theAmount) {
        TheAmount = theAmount;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        PaymentDate = paymentDate;
    }

    public Date getBorrowingDate() {
        return BorrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        BorrowingDate = borrowingDate;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}
