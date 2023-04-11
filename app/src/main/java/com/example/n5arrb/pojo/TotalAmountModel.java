package com.example.n5arrb.pojo;

public class TotalAmountModel {
     String dibet;
     String credit;
     String Balance;

    public TotalAmountModel(String dibet, String credit, String balance) {
        this.dibet = dibet;
        this.credit = credit;
        Balance = balance;
    }

    public String getDibet() {
        return dibet;
    }

    public void setDibet(String dibet) {
        this.dibet = dibet;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }
}
