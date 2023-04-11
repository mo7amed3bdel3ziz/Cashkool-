package com.example.n5arrb.pojo;

import com.google.type.DateTime;

import java.util.Date;

public class AllTransaction {

    private Double TheAmount;
    private String PaymentDate;
    private String Details;
    private String BorrowingDate;
    private Integer Client_notify;
    private Integer Id;
    private String ClientPhone;
    private String Guid;
    private Integer UserID;
    private Integer Credit_Depit;
    private String ClientName;

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

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getBorrowingDate() {
        return BorrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        BorrowingDate = borrowingDate;
    }

    public Integer getClient_notify() {
        return Client_notify;
    }

    public void setClient_notify(Integer client_notify) {
        Client_notify = client_notify;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getCredit_Depit() {
        return Credit_Depit;
    }

    public void setCredit_Depit(Integer credit_Depit) {
        Credit_Depit = credit_Depit;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }
}
