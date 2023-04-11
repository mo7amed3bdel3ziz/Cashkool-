package com.example.n5arrb.contactesAccess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n5arrb.AddActivity;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.allTransaction.AllTransactionForOneClaActivity;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsDatabase;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsRoom;
import com.example.n5arrb.databinding.ActivityAddCustmwe6Binding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ContactsActivity extends AppCompatActivity {
    List<ContactsRoom> contactUsers;
    private static final int PERMISSION_CODE = 1234;
    private static final int RQ = 1;
    ActivityAddCustmwe6Binding binding;
    AdabteContact adapter;
    // ContactsDatabase contactsDatabase;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_custmwe6);
        contactUsers=new ArrayList<>();
        binding= DataBindingUtil.setContentView(this, R.layout.activity_add_custmwe6);
        // readContacts();

        checkPermission(Manifest.permission.WRITE_CONTACTS, PERMISSION_CODE);

        searchViewSetup();
        adapter=new AdabteContact(this);


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(AllTransactionForOneClaActivity.this, salary, Toast.LENGTH_SHORT).show();

                BottomSheetDialog sheet = new BottomSheetDialog(
                        ContactsActivity.this, R.style.Theme_Design_BottomSheetDialog
                );
                View btnSheet = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.sheet_add_contact,
                                (LinearLayout) findViewById(R.id.sheetAdd));


                EditText name = btnSheet.findViewById(R.id.btn_name);

                //  name.setText("ssss");
                //   name1.setText(name);

                // Toast.makeText(ContactsActivity.this, names, Toast.LENGTH_SHORT).show();



                EditText phone = btnSheet.findViewById(R.id.btn_phone);
                // phone.setText("ssss");




                Button addUser=btnSheet.findViewById(R.id.btn_add);

                addUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phoneF= phone.getText().toString();
                        String names= name.getText().toString();

                        // Toast.makeText(ContactsActivity.this, y, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(ContactsActivity.this, x, Toast.LENGTH_SHORT).show();

                        ArrayList<ContentProviderOperation> op_list = new ArrayList<ContentProviderOperation>();
                        op_list.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                                //.withValue(RawContacts.AGGREGATION_MODE, RawContacts.AGGREGATION_MODE_DEFAULT)
                                .build());

                        // first and last names
                        op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, names)
                                //.withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, "First Name hag")
                                .build());

                        op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneF)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                                .build());


                        try{


                            getContentResolver().applyBatch(ContactsContract.AUTHORITY, op_list);
                            ContactsRoom post=new ContactsRoom(names,phoneF,"peter@tbi.com");
                            ContactsDatabase postsDataBass =  ContactsDatabase.getGetInstance(ContactsActivity.this);
                            //  postsDataBass.contactsDao().insertContacts()
                            postsDataBass.contactsDao().insertContacts(post)

                                    .subscribeOn(Schedulers.computation())

                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }

                                        @Override
                                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                        }
                                    });
                            Toast.makeText(ContactsActivity.this, "Add", Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(ContactsActivity.this,AddActivity.class);
                            i.putExtra("name",names);
                            i.putExtra("num",phoneF);
                            //    i.putExtra("state","1");
                            startActivity(i);
                            finish();

                        }catch(Exception e){
                            e.printStackTrace();
                            Toast.makeText(ContactsActivity.this,  e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(ContactsActivity.this, "ttttttost", Toast.LENGTH_SHORT).show();

                    }
                });


                //  salarys.setText(salary);
                // textView33.setText(salary);
                // textView37.setText(discreptin);

                // addUser.findViewById(R.id.constraintLayout2)


                //         .setOnClickListener(new View.OnClickListener() {
                //             @Override
                //             public void onClick(View view) {
                //                 String phoneNumberWithCountryCode = phones;
                //                 String message = "Hallo!!  From Kashcool " +
                //                         "you have received an invitation to download Kashcool";

                //                 startActivity(
                //                         new Intent(Intent.ACTION_VIEW,
                //                                 Uri.parse(
                //                                         String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                //                                 )
                //                         )
                //                 );
                //             }
                //         });


                sheet.setContentView(btnSheet);
                sheet.show();
            }
        });
        //    ArrayList<ContactUser> s=new ArrayList<>();
        //    s.add(new ContactUser("s","d","d"));
        //   // ContactsRoom c=new ContactsRoom();
        binding.contactsrecy.setLayoutManager(new LinearLayoutManager(this));

        //    adapter.setContactUserslist(s);
        ////adapter.notifyDataSetChanged();
        //    binding.contactsrecy.setAdapter(adapter);

        ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(this);
        contactsDatabase.contactsDao().getContacts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ContactsRoom>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ContactsRoom> contactsRooms) {
                        // Log.d("yousiiiif",contactsRooms.get(1).getNumber().toString());

                        contactUsers=contactsRooms;

                        adapter.setContactUserslist(contactsRooms);
                        //  for (ContactsRoom r:contactsRooms){
                        //      Log.i("Contact","name:"+r.getName()+"number"+r.getNumber()+"email"+r.getEmail());
                        //  }
                        // adapter.notifyDataSetChanged();
//
                        binding.contactsrecy.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("yousiiiif",e.getMessage().toString());

                    }
                });

        // binding.button3.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         startActivity(new Intent(ContactsActivity.this, AddActivity.class));
        //     }
        // });


    }
    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<ContactsRoom> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (ContactsRoom item : contactUsers) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }
    private void searchViewSetup(){
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
    }
    //  if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=
    //          PackageManager.PERMISSION_GRANTED){
    //      ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},
    //            RQ);
    //  } else {
    // populateDataIntoRecyclerView(contactUsers);
    //   contactsDatabase=ContactsDatabase.getGetInstance(this);
    //   contactsDatabase.contactsDao().getContacts()
    //           .subscribeOn(Schedulers.computation())
    //           .observeOn(AndroidSchedulers.mainThread())
    //           .subscribe(new SingleObserver<List<ContactsRoom>>() {
    //               @Override
    //               public void onSubscribe(@NonNull Disposable d) {

    //               }

    //               @Override
    //               public void onSuccess(@NonNull List<ContactsRoom> contactsRooms) {
    //                //   populateDataIntoRecyclerView(contactsRooms);

    //               }

    //               @Override
    //               public void onError(@NonNull Throwable e) {

    //               }
    //           });
    //  new MyTask().execute();
    // obsx();
//            ContactsDatabase contactsDatabase=ContactsDatabase.getGetInstance(this);
//            contactsDatabase.contactsDao().getContacts()
//                    .subscribeOn(Schedulers.computation())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new SingleObserver<List<ContactsRoom>>() {
//                @Override
//                public void onSubscribe(@NonNull Disposable d) {
//
//                }
//
//                @Override
//                public void onSuccess(@NonNull List<ContactsRoom> contactsRooms) {
//
//                }
//
//                @Override
//                public void onError(@NonNull Throwable e) {
//
//                }
//            });
    //.subscribeOn(Schedulers.computation()).
    //observeOn(AndroidSchedulers.mainThread())
    // .subscribe(new SingleObserver<List<ContactsRoom>>() {
    //     @Override
    //     public void onSubscribe(@NonNull Disposable d) {

    //     }

    //     @Override
    //     public void onSuccess(@NonNull List<ContactsRoom> contactsRooms) {

    //     }

    //     @Override
    //     public void onError(@NonNull Throwable e) {

    //     }
    // });

    //  }




    // @SuppressLint("Range")
    // private ArrayList< ContactUser> readContacts() {
    //     contactUsers=new ArrayList<>();
    //     Uri uri= ContactsContract.Contacts.CONTENT_URI;
    //     Cursor cursor =getContentResolver().query(uri,null,null,null,
    //             ContactsContract.Contacts.DISPLAY_NAME+"ds");
    //     if (cursor.moveToFirst()){
    //         do{
    //             long id=cursor.getLong(cursor.getColumnIndex("_ID"));
    //             Uri uri1=ContactsContract.Data.CONTENT_URI;
    //             Cursor cursor1=getContentResolver().query(uri1,null,ContactsContract.Data
    //                     .CONTACT_ID+ "=?",new String[]{String.valueOf(id)},null);

    //             String name="";
    //             String phone="";
    //             String contactOtherDetails = "";
    //             if (cursor1.moveToFirst()){
    //                 name=cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    //                 do{
    //                     //if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
    //                     // .CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
    //                     //     name=cursor1.getString(cursor1.getColumnIndex("data1"));
    //                     //    contactOtherDetails += name + "\n";
    //                     //}
    //                     if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
    //                             .CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
    //                         switch (cursor1.getInt(cursor1
    //                                 .getColumnIndex("data2"))) {

    //                             case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
    //                                 phone = cursor1.getString(cursor1
    //                                         .getColumnIndex("data1"));

    //                                 break;
    //                         }
    //                     }
    //                 }while (cursor1.moveToNext());
    //             }


    //        //   contactsDatabase.contactsDao().insertContacts(new ContactsRoom(name,phone,"dd"))
    //        //           .subscribeOn(Schedulers.computation())
    //        //           .subscribe(new CompletableObserver() {
    //        //               @Override
    //        //               public void onSubscribe(@NonNull Disposable d) {

    //        //               }

    //        //               @Override
    //        //               public void onComplete() {

    //        //               }

    //        //               @Override
    //        //               public void onError(@NonNull Throwable e) {

    //        //               }
    //        //           });
    //            contactUsers.add(new  ContactUser(name,phone,"dd"));
    //         }while (cursor.moveToNext());
    //     }

    //   return contactUsers;
    // }


    // Observable ob = Observable.create(new ObservableOnSubscribe<ContactsRoom>() {
    //     @Override
    //     public void subscribe(@NonNull ObservableEmitter<ContactsRoom> emitter) throws Exception {

    //     }
    // });
//    public void obsx(){
//        Observable ob =Observable.create(new ObservableOnSubscribe<ArrayList<ContactUser>>() {
//          @Override
//          public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<ArrayList<ContactUser> >emitter) throws Throwable {
//              ArrayList<ContactUser> contactUserArrayList=readContacts();
//              // for (ContactUser x:contactUserArrayList){
//              //     emitter.onNext(x);
//              // }
//
//              emitter.onNext(contactUserArrayList);
//          }
//      }
//        ) . subscribeOn(Schedulers.io())
//                . observeOn(AndroidSchedulers.mainThread());
//        Observer obs= new Observer<ArrayList<ContactUser>>() {
//            @Override
//            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@io.reactivex.rxjava3.annotations.NonNull ArrayList<ContactUser> contactUsers) {
//
//                populateDataIntoRecyclerView(contactUsers);
//            }
//
//            @Override
//            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        ob.subscribe(obs);
//
//
//    }


    //   @SuppressLint("Range")
    //   private void readContacts() {
    //       contactUsers=new ArrayList<>();
    //       Uri uri= ContactsContract.Contacts.CONTENT_URI;
    //       Cursor cursor =getContentResolver().query(uri,null,null,null,
    //               ContactsContract.Contacts.DISPLAY_NAME+"ds");
    //       if (cursor.moveToFirst()){
    //           do{
    //               long id=cursor.getLong(cursor.getColumnIndex("_ID"));
    //               Uri uri1=ContactsContract.Data.CONTENT_URI;
    //               Cursor cursor1=getContentResolver().query(uri1,null,ContactsContract.Data
    //               .CONTACT_ID+ "=?",new String[]{String.valueOf(id)},null);
    //
    //               String name="";
    //               String phone="";
    //               String contactOtherDetails = "";
    //               if (cursor1.moveToFirst()){
    //                   name=cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    //                   do{
    //                     // if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
    //                     //  .CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
    //                     //      name=cursor1.getString(cursor1.getColumnIndex("data1"));
    //                     //     contactOtherDetails += name + "\n";
    //                     // }
    //                       if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
    //                               .CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
    //                          switch (cursor1.getInt(cursor1
    //                                  .getColumnIndex("data2"))) {
    //
    //                              case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
    //                                  phone = cursor1.getString(cursor1
    //                                          .getColumnIndex("data1"));
    //
    //                                  break;
    //                          }
    //                       }
    //                   }while (cursor1.moveToNext());
    //               }
    //
    //               contactUsers.add(new ContactUser(Long.toString(id),name,phone,"dd"));
    //           }while (cursor.moveToNext());
    //       }
    //
    //   }
    //
    //
    // class MyTask extends AsyncTask<Void, Void, Void> {
//
    //     @Override
    //     protected void onPreExecute() {
    //         super.onPreExecute();
//
    //     }
//
    //     @Override
    //     protected Void doInBackground(Void... voids) {
    //         readContacts();
    //         return null;
    //     }
//
    //     @Override
    //     protected void onPostExecute(Void aVoid) {
    //         super.onPostExecute(aVoid);
//
    //        populateDataIntoRecyclerView(contactUsers);
    //     }
    // }

//
    // private void populateDataIntoRecyclerView(List< ContactUser> items) {
    //    AdabteContact adapter=new AdabteContact(items,this);
//
//
    //     binding.contactsrecy.setAdapter(adapter);
    //     binding.contactsrecy.setLayoutManager(new LinearLayoutManager(this));
    //     binding.contactsrecy.setHasFixedSize(true);
    // }




    //  @Override
    //  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    //      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    //      if (requestCode==RQ&&grantResults.length>0){
    //          Log.d("loooood","pee");
    //       //   readContacts();
    //      }
    //  }
// @SuppressLint("Range")
// private ArrayList<ContactUser> readContacts() {
//      contactUsers=new ArrayList<>();
//     Uri uri= ContactsContract.Contacts.CONTENT_URI;
//     Cursor cursor =getContentResolver().query(uri,null,null,null,
//             ContactsContract.Contacts.DISPLAY_NAME+"ds");
//     if (cursor.moveToFirst()){
//         do{
//             long id=cursor.getLong(cursor.getColumnIndex("_ID"));
//             Uri uri1=ContactsContract.Data.CONTENT_URI;
//             Cursor cursor1=getContentResolver().query(uri1,null,ContactsContract.Data
//                     .CONTACT_ID+ "=?",new String[]{String.valueOf(id)},null);
//
//             String name="";
//             String phone="";
//             String contactOtherDetails = "";
//             if (cursor1.moveToFirst()){
//                 name=cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                 do{
//                     //if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
//                     // .CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
//                     //     name=cursor1.getString(cursor1.getColumnIndex("data1"));
//                     //    contactOtherDetails += name + "\n";
//                     //}
//                     if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
//                             .CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
//                         switch (cursor1.getInt(cursor1
//                                 .getColumnIndex("data2"))) {
//
//                             case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
//                                 phone = cursor1.getString(cursor1
//                                         .getColumnIndex("data1"));
//
//                                 break;
//                         }
//                     }
//                 }while (cursor1.moveToNext());
//             }
//
//             contactUsers.add(new ContactUser(Long.toString(id),name,phone,"dd"));
//         }while (cursor.moveToNext());
//     }
//
//     return contactUsers;
// }
// public void obsx(){
//      Observable ob =Observable.create(new ObservableOnSubscribe<ArrayList<ContactUser>>() {
//          @Override
//          public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<ArrayList<ContactUser> >emitter) throws Throwable {
//             ArrayList<ContactUser> contactUserArrayList=readContacts();
//            // for (ContactUser x:contactUserArrayList){
//            //     emitter.onNext(x);
//            // }
//
//              emitter.onNext(contactUserArrayList);
//          }
//      }
//      ) . subscribeOn(Schedulers.io())
//              . observeOn(AndroidSchedulers.mainThread());
//              Observer obs= new Observer<ArrayList<ContactUser>>() {
//                  @Override
//                  public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                  }
//
//                  @Override
//                  public void onNext(@io.reactivex.rxjava3.annotations.NonNull ArrayList<ContactUser> contactUsers) {
//
//                      populateDataIntoRecyclerView(contactUsers);
//                  }
//
//                  @Override
//                  public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                  }
//
//                  @Override
//                  public void onComplete() {
//
//                  }
//              };
// ob.subscribe(obs);
//
//
//  }
    private void checkPermission(String permission, int requestCode) {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(ContactsActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{permission}, requestCode);
        } else {
            // saveData();
            Toast.makeText(ContactsActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(ContactsActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                // saveData();
            } else {
                Toast.makeText(ContactsActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }
}