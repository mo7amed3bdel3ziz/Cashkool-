package com.example.n5arrb.pojo;

public class UserClientPhones {
   String UserPhone;
    String ClientPhone;

    public UserClientPhones(String userPhone, String clientPhone) {
        UserPhone = userPhone;
        ClientPhone = clientPhone;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }
}
