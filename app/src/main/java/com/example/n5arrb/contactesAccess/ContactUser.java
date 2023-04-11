package com.example.n5arrb.contactesAccess;

public class ContactUser {
    String id,name,number,email;

    public ContactUser(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public ContactUser(String id, String name, String number, String email) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
