package com.example.n5arrb.pojo.auth;

public class OtpModel {
      boolean IsSuccess;
      String  message;
      String  UserPhone;
      int State;

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

}
