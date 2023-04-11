package com.example.n5arrb.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.n5arrb.UI.allTransaction.AllTransactionForOneClaActivity;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsDatabase;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsRoom;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.AllClientsModel;
import com.example.n5arrb.pojo.TotalAmountModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryHome {

  //  Context context;
//
  //  public RepositoryHome(Context context) {
  //      this.context = context;
  //  }


    // نتاكد من الباصص
    //
    MutableLiveData<List<AllClientsModel>> clientsModelMutableLiveData=new MutableLiveData<>();
    MutableLiveData<TotalAmountModel> TotalAmounts=new MutableLiveData<>();
    MutableLiveData<String> eroor=new MutableLiveData<>();


    public void getTotalAmount(String numer){
        Observable TotalAmount = TransactionsServiceRetrofit.getInstans()
                .getApiService().getTotalAmount(numer)
                . subscribeOn(Schedulers.io())
                . observeOn(AndroidSchedulers.mainThread());

        Observer<TotalAmountModel> Total= new Observer<TotalAmountModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("yoyo","Disposable"+d.toString());
            }
            @Override
            public void onNext(@NonNull TotalAmountModel totalAmountModel) {
                TotalAmounts.setValue(totalAmountModel);
              // Log.d("yoyo","ddddddddddddd");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("yoyo",e.getMessage()+"llllllll");
            }
            @Override
            public void onComplete() {
                Log.d("yoyo","e.getMessage()");
            }
        };
        TotalAmount.subscribe(Total);

    }

    public void
    //getAllClintss
    getAllClints (String numer,Context context){
         Observable AllClints = TransactionsServiceRetrofit.getInstans()
                 .getApiService().getAllClintsO(numer)
                 .subscribeOn(Schedulers.io())
                 .map(new Function<List<AllClientsModel>,List<AllClientsModel>>() {
                     @Override
                     public List<AllClientsModel> apply(List<AllClientsModel> allClientsModels) throws Throwable {
                         List<AllClientsModel>list=allClientsModels;
                       //  Double x=0.0;
                       //  Double y=0.0;
                         for (AllClientsModel all:list){
                             String fphone=  all.getClientPhone().replace("+2","");
//
                             Log.d("Fons",fphone);
                             ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(context);
                             // Log.i("FilterPhone",phones);
                             contactsDatabase.contactsDao().getNumContacsts(fphone)
                                     //  .subscribeOn(Schedulers.computation())
                                     .subscribeOn(Schedulers.io())
                                     .observeOn(Schedulers.io())
                                     .subscribe(new SingleObserver<String>() {
                                         @Override
                                         public void onSubscribe(@androidx.annotation.NonNull Disposable d) {
//
                                         }
                                         //
                                         @Override
                                         public void onSuccess(@androidx.annotation.NonNull String s) {

                                             all.setNewName(s);
                                             //   clientslist.get(position).setClientName(s);
                                             //   holder. name69.setText( clientslist.get(position).getClientName())     ;
                                             //   //  Toast.makeText(AllTransactionForOneClaActivity.this, "Name aho"+s, Toast.LENGTH_SHORT).show();
                                             //   //   holder. name69.setText(clientslist.get(position).getClientPhone());
                                             //  if(s.length()!=0){
                                             //      all.setNewName(s);
                                             //  }if (s==null){
                                             //         all.setNewName("غير معرف");

                                             //     }
                                             // }else {
                                             //     String sa=null;
                                             //     all.setNewName("غير معرف");

                                             // }

                                             Log.d("Fons",s);
                                         }
                                         ////
                                         @Override
                                         public void onError(@androidx.annotation.NonNull Throwable e) {
                                             Log.d("Fons",e.getMessage());
////
                                             all.setNewName("غير معروف");
                                         }
                                     });


                             //  if (all.getTotalAmount()>0){
                             //      x+= all.getTotalAmount();
                             //  }
                             //  if (all.getTotalAmount()<=0){
                             //      y+=all.getTotalAmount();
                             //  }
                         }
                       // list.get(0).setCredit(x);
                       // list.get(0).setDepit(y);

                         return list;
                     }
                 })
                 .observeOn(AndroidSchedulers.mainThread());
         Observer <List<AllClientsModel>> AllClintss=new Observer<List<AllClientsModel>>() {
             @Override
             public void onSubscribe(@NonNull Disposable d) {

             }

             @Override
             public void onNext(@NonNull List<AllClientsModel> allClientsModels) {

                 clientsModelMutableLiveData.setValue(allClientsModels);
             }

             @Override
             public void onError(@NonNull Throwable e) {
               //  eroor.setValue(e.getMessage());
               //  clientsModelMutableLiveData.setValue(null);

             }

             @Override
             public void onComplete() {

             }
         };

        AllClints.subscribe(AllClintss);
    }

    public void getAllClintsWith(String numer,Context context) {
        String s = "+201000399179";

      // Observable AllClints = TransactionsServiceRetrofit.getInstans()
      //         .getApiService().getAllClints(numer)
      //         .subscribeOn(Schedulers.io())
      //         .map(new Function<List<AllClientsModel>,List<AllClientsModel>>() {
      //             @Override
      //             public List<AllClientsModel> apply(List<AllClientsModel> allClientsModels) throws Throwable {
      //                 List<AllClientsModel>list=allClientsModels;
      //                 Double x=0.0;
      //                 Double y=0.0;
      //                 for (AllClientsModel all:list){
      //                   String fphone=  all.getClientPhone().replace("+2","");
//
      //                   Log.d("phonsss",fphone);
      //                    ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(context);
      //                    // Log.i("FilterPhone",phones);
      //                    contactsDatabase.contactsDao().getNumContacsts(fphone)
      //                            .subscribeOn(Schedulers.computation())
      //                            .observeOn(AndroidSchedulers.mainThread())
      //                            .subscribe(new SingleObserver<String>() {
      //                                @Override
      //                                public void onSubscribe(@androidx.annotation.NonNull Disposable d) {
//
      //                                }
//
      //                                @Override
      //                                public void onSuccess(@androidx.annotation.NonNull String s) {
      //                                //   clientslist.get(position).setClientName(s);
      //                                //   holder. name69.setText( clientslist.get(position).getClientName())     ;
      //                                //   //  Toast.makeText(AllTransactionForOneClaActivity.this, "Name aho"+s, Toast.LENGTH_SHORT).show();
      //                                //   //   holder. name69.setText(clientslist.get(position).getClientPhone());
      //                                    if(s.trim().length()!=0){
      //                                        all.setNewName(s.trim());
      //                                    }else {
      //                                        String sa=null;
      //                                        all.setNewName("غير معرف");

      //                                    }

      //                                    Log.d("Fons",s);
      //                                }
////
      //                            @Override
      //                              public void onError(@androidx.annotation.NonNull Throwable e) {
////
      //                              }
      //                          });


      //                     if (all.getTotalAmount()>0){
      //                         x+= all.getTotalAmount();
      //                     }
      //                     if (all.getTotalAmount()<=0){
      //                         y+=all.getTotalAmount();
      //                     }
      //                 }
      //                 list.get(0).setCredit(x);
      //                 list.get(0).setDepit(y);

      //                 return list;
      //             }
      //         })
      //         .observeOn(AndroidSchedulers.mainThread());
       @NonNull Single<List<AllClientsModel>> AllClints = TransactionsServiceRetrofit.getInstans()
                .getApiService().getAllClints(numer)
               // .subscribeOn(Schedulers.io())
                .map(new Function<List<AllClientsModel>,List<AllClientsModel>>() {
                    @Override
                    public List<AllClientsModel> apply(List<AllClientsModel> allClientsModels) throws Throwable {
                        List<AllClientsModel>list=allClientsModels;
                       // Double x=0.0;
                       // Double y=0.0;
                        for (AllClientsModel all:list){
                          String fphone=  all.getClientPhone().replace("+2","");
//
                          Log.d("Fons",fphone);
                          ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(context);
                          // Log.i("FilterPhone",phones);
                          contactsDatabase.contactsDao().getNumContacsts(fphone)
                                //  .subscribeOn(Schedulers.computation())
                                  .subscribeOn(Schedulers.io())
                                  .observeOn(Schedulers.io())
                                  .subscribe(new SingleObserver<String>() {
                                      @Override
                                      public void onSubscribe(@androidx.annotation.NonNull Disposable d) {
//
                                      }
                                      //
                                      @Override
                                      public void onSuccess(@androidx.annotation.NonNull String s) {

                                          all.setNewName(s);
                                          //   clientslist.get(position).setClientName(s);
                                          //   holder. name69.setText( clientslist.get(position).getClientName())     ;
                                          //   //  Toast.makeText(AllTransactionForOneClaActivity.this, "Name aho"+s, Toast.LENGTH_SHORT).show();
                                          //   //   holder. name69.setText(clientslist.get(position).getClientPhone());
                                    //  if(s.length()!=0){
                                    //      all.setNewName(s);
                                    //  }if (s==null){
                                    //         all.setNewName("غير معرف");

                                    //     }
                                     // }else {
                                     //     String sa=null;
                                     //     all.setNewName("غير معرف");

                                     // }

                                         Log.d("Fons",s);
                                      }
                                      ////
                                      @Override
                                      public void onError(@androidx.annotation.NonNull Throwable e) {
                                          Log.d("Fons",e.getMessage());
////
                                          all.setNewName("غير معروف");
                                      }
                                  });


                         //  if (all.getTotalAmount()>0){
                         //      x+= all.getTotalAmount();
                         //  }
                         //  if (all.getTotalAmount()<=0){
                         //      y+=all.getTotalAmount();
                         //  }
                        }
                      //  list.get(0).setCredit(x);
                      //  list.get(0).setDepit(y);

                        return list;
                    }
                }) .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread());



       SingleObserver <List<AllClientsModel>> singleObserver=new SingleObserver<List<AllClientsModel>>() {
           @Override
           public void onSubscribe(@NonNull Disposable d) {
            //   clientsModelMutableLiveData.setValue(null);

               Log.d("hend",d.toString());

           }

           @Override
           public void onSuccess(@NonNull List<AllClientsModel> allClientsModels) {
               clientsModelMutableLiveData.setValue(allClientsModels);

           }

           @Override
           public void onError(@NonNull Throwable e) {
               clientsModelMutableLiveData.setValue(null);


           //   Log.d("hend",e.getMessage());
           }
       };
        AllClints.subscribe(singleObserver);
    }
    //   Single DownAllClints= new Single<List<AllClientsModel>>()
      // Single<List<AllClientsModel>> DownAllClints= new Single<List<AllClientsModel>>() {
      //      @Override
      //      public void onSubscribe(@NonNull Disposable d) {
      //      }
      //      @Override
      //      public void onNext(@NonNull List<AllClientsModel> transactions) {

      //          clientsModelMutableLiveData.setValue(transactions);
      //      }
      //      @Override
      //      public void onError(@NonNull Throwable e) {
      //      }
      //      @Override
      //      public void onComplete() {
      //      }
      //  };
      //  AllClints.subscribe(DownAllClints);


  // public void getClain(String numer,Context context){
  //     Observable AllClints = TransactionsServiceRetrofit.getInstans()
  //          .getApiService()
  //          .getAllClints(numer)
  //         //.create(new ObservableOnSubscribe<List<AllClientsModel>>() {
  //         //    @Override
  //         //    public void subscribe(@NonNull ObservableEmitter<List<AllClientsModel>> emitter) throws Throwable {
  //         //        ArrayList<ContactsRoom> postArrayList=readContacts();
  //         //        for (ContactsRoom x:postArrayList) {
  //         //            emitter.onNext(x);

  //         //        }
  //         //    }
  //         //})
  //             .zipWith()
  //             .subscribeOn(Schedulers.io())
  //
  // }



    public MutableLiveData<List<AllClientsModel>> getClientsModelMutableLiveData() {
        return clientsModelMutableLiveData;
    }

    public MutableLiveData<TotalAmountModel> getTotalAmountss() {
        return TotalAmounts;
    }


  // public String getName(String phone){
  //
  //     String a="";
  //
  //     ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(this);
  //    // Log.i("FilterPhone",phones);
  //     contactsDatabase.contactsDao().getNumContacsts(phone)
  //             .subscribeOn(Schedulers.computation())
  //             .observeOn(AndroidSchedulers.mainThread())
  //             .subscribe(new SingleObserver<String>() {
  //                 @Override
  //                 public void onSubscribe(@NonNull Disposable d) {

  //                 }

  //                 @Override
  //                 public void onSuccess(@NonNull String s) {
  //                   //  Toast.makeText(AllTransactionForOneClaActivity.this, "Name aho"+s, Toast.LENGTH_SHORT).show();

  //
  //                 }

  //                 @Override
  //                 public void onError(@NonNull Throwable e) {

  //                 }
  //             });
  //
  // }
}
