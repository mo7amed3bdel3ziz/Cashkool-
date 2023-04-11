package com.example.n5arrb.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.n5arrb.pojo.NotifayModel;
import com.example.n5arrb.repository.RepositoryNotifay;

import java.util.List;

public class NotifayViewModel extends ViewModel {
    RepositoryNotifay notifay;
    MutableLiveData<List<NotifayModel>> NotifayLiveData=new MutableLiveData<>();

    public NotifayViewModel() {
        notifay=new RepositoryNotifay();
    }

    public void getNotifay(String phone){
        notifay.getNotifayRep(phone);
        NotifayLiveData= notifay.getNotifayLiveData();

    }

    public MutableLiveData<List<NotifayModel>> getNotifayLiveData() {
        return NotifayLiveData;
    }
}
