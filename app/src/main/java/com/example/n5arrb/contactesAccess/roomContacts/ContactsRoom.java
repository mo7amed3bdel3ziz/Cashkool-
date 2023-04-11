package com.example.n5arrb.contactesAccess.roomContacts;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ContactsTable",indices = @Index(value = {"name","number"},unique = true))

public class ContactsRoom {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "number")
    String number;
    @ColumnInfo(name = "email")
    String email;

    public ContactsRoom(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
      //  Log.i("InitContact",""+name+number+email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


