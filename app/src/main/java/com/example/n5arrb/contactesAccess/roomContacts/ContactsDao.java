package com.example.n5arrb.contactesAccess.roomContacts;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ContactsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(ContactsRoom user);

    @Query("select*from ContactsTable")
    Single<List<ContactsRoom>> getContacts();

 //string query = "Select * From table Where number"
    @Query("select name from ContactsTable where number = :num")
    Single<String> getNumContacsts(String num);
//
  //  @Query("select * from ContactsTable where number = :num")
  //  Single<ContactsRoom> getNumContacts(String num);
  // // = null;
}
