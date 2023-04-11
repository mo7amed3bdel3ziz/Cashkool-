package com.example.n5arrb.pojo;

public class TotalAmount {

     private   Double TotalCredits=0.0;
     private   Double TotalDepits=0.0;
     private   Double netbalance;

  // public TotalAmount() {
  //     this.TotalCredits =0.00000;
  //     this.TotalDepits = 0.00000;
  //     this.netbalance = netbalance;
  // }


    public Double getNetbalance() {
        return netbalance;
    }

    public void setNetbalance(Double netbalance) {
        this.netbalance = netbalance;
    }

    public Double getTotalCredits() {
        return TotalCredits;
    }

    public void setTotalCredits(Double totalCredits) {

        TotalCredits = totalCredits;
    }

    public Double getTotalDepits() {
        return TotalDepits;
    }

    public void setTotalDepits(Double totalDepits) {

        TotalDepits = totalDepits;
    }
}
