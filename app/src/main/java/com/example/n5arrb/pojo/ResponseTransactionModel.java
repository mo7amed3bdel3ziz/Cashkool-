package com.example.n5arrb.pojo;

public class ResponseTransactionModel {
    public int Id;
    double TheAmount;
    String PaymentDate;
    String BorrowingDate;
    int Client_notify;
    String Details;
    String ClientName;
    String ClientPhone;
    String Guid;
    String UserID;
    int Left_Day;
    String Transaction_Date;
    String Users_TBL ;

    public double getTheAmount() {
        return TheAmount;
    }

    public void setTheAmount(double theAmount) {
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

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getLeft_Day() {
        return Left_Day;
    }

    public void setLeft_Day(int left_Day) {
        Left_Day = left_Day;
    }

    public String getTransaction_Date() {
        return Transaction_Date;
    }

    public void setTransaction_Date(String transaction_Date) {
        Transaction_Date = transaction_Date;
    }

    public String getUsers_TBL() {
        return Users_TBL;
    }

    public void setUsers_TBL(String users_TBL) {
        Users_TBL = users_TBL;
    }
}