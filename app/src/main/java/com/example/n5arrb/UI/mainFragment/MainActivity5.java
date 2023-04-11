package com.example.n5arrb.UI.mainFragment;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.n5arrb.R;
import com.example.n5arrb.UI.LocaleStorageJVM;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsDatabase;
import com.example.n5arrb.contactesAccess.roomContacts.ContactsRoom;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity5 extends AppCompatActivity {
    private final int home = 1;
    private final int notify = 2;
    private final int setting = 3;
    private static final int RQ = 1;

    int num = 123456789;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        String ArNum = convertToArabic(num);
        Log.d("number", ArNum);
        LocaleStorageJVM localeStorageJVM = new LocaleStorageJVM(getBaseContext());
        localeStorageJVM.onAttach("ar");
        Toast.makeText(this, localeStorageJVM.getLanguage(), Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    RQ);
        } else {
            obsx();
        }

        //  HomeViewModel viewModels=new ViewModelProvider(MainActivity5.this).get(HomeViewModel.class);
        //  viewModels.getTotalAmount("+201155447106");
        //  viewModels.getallClint("+201155447106");

        LocaleStorageJVM localeStorageJVM1 = new LocaleStorageJVM(MainActivity5.this);
        localeStorageJVM1.setDefaultLanguage("ar");
        localeStorageJVM1.onAttach("ar");

        localeStorageJVM1.onAttach();
        setLocale(localeStorageJVM1.getLanguage());

        MeowBottomNavigation bottomNavigation = findViewById(R.id.s);
        bottomNavigation.add(new MeowBottomNavigation.Model(home, R.drawable.ic_baseline_add_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(notify, R.drawable.ic_baseline_notifications_none_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(setting, R.drawable.ic_baseline_settings_24));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                return null;
            }
        });


        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case home:
                        homeFragment mainFragment = new homeFragment();
                        replace(mainFragment);
                        break;

                    case notify:
                        notifayFragment fragment = new notifayFragment();
                        replace(fragment);
                        break;

                    case setting:
                        settingFragment settingFragment = new settingFragment();
                        replace(settingFragment);

                }
                return null;
            }

        });
        bottomNavigation.show(home, true);


    }

    public void replace(Fragment fragment) {
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        //  FragmentTransaction ft=fragmentManager.beginTransaction();
        // ft.add()

        fragmentManager.replace(R.id.fra, fragment);
        fragmentManager.commit();
    }

    @SuppressLint("Range")
    private ArrayList<ContactsRoom> readContacts() {
        ArrayList<ContactsRoom> p = new ArrayList<>();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        Cursor cursor = getContentResolver().query(uri, null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME);
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex("_ID"));
                Uri uri1 = ContactsContract.Data.CONTENT_URI;
                Cursor cursor1 = getContentResolver().query(uri1, null, ContactsContract.Data
                        .CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);

                String name = "";
                String phone = "";
                String contactOtherDetails = "";
                if (cursor1.moveToFirst()) {
                    name = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    do {
                        //if( cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
                        //.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
                        // name=cursor1.getString(cursor1.getColumnIndex("data1"));
                        // contactOtherDetails += name + "\n";
                        //}
                        if (cursor1.getString(cursor1.getColumnIndex("mimetype")).equals(ContactsContract
                                .CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
                            switch (cursor1.getInt(cursor1.getColumnIndex("data2"))) {

                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    phone = cursor1.getString(cursor1
                                            .getColumnIndex("data1"));
                                    break;
                            }
                        }
                    }
                    while (cursor1.moveToNext());
                }
                String newName = phone.replace("+2", "").replace("-", "")
                        .replace(" ", "");
                //  newName= phone.replace("--","");
                if (name.isEmpty() && newName.isEmpty() && name == null && newName == null) {

                } else {
                    p.add(new ContactsRoom(name, newName, "peter@tbi.com"));
                }

            } while (cursor.moveToNext());
        }
        return p;
    }

    public void obsx() {

        Observable ob = Observable.create(new ObservableOnSubscribe<ContactsRoom>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<ContactsRoom> emitter) throws Exception {
                        ArrayList<ContactsRoom> postArrayList = readContacts();
                        for (ContactsRoom x : postArrayList) {
                            emitter.onNext(x);
                        }
                    }
                }).subscribeOn(io())
                .observeOn(computation());

        Observer obs = new Observer<ContactsRoom>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ContactsRoom post) {
                //     Log.i("intcooont","name"+post.getName()+"numper"+post.getNumber());

                ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(getApplicationContext());
                postsDataBass.contactsDao().insertContacts(post)
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onComplete() {
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(MainActivity5.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(MainActivity5.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {

            }
        };
        ob.subscribe(obs);


    }


    //public  void add(String name, String num){
    //    PostsDataBass postsDataBass = PostsDataBass.getInstance(SingelActivity2.this);
    //    postsDataBass.Dao().insertpost(new Post(1, name, num))
    //            .subscribeOn(computation())
    //            .subscribe(new CompletableObserver() {
    //                @Override
    //                public void onSubscribe(@NonNull Disposable d) {
    //                }
    //                @Override
    //                public void onComplete() {
    //                }
    //                @Override
    //                public void onError(@NonNull Throwable e) {
    //                }
    //            });
    // }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RQ && grantResults.length > 0) {
            //  Log.d("loooood","pee");
            //  readContacts();
            obsx();
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        // Intent refresh = new Intent(SplashActivity.this, MainActivity5.class);
        // startActivity(refresh);
    }

    public String convertToArabic(int value) {
        String newValue = (((((((((((value + "")
                .replaceAll("1", "١")).replaceAll("2", "٢"))
                .replaceAll("3", "٣")).replaceAll("4", "٤"))
                .replaceAll("5", "٥")).replaceAll("6", "٦"))
                .replaceAll("7", "٧")).replaceAll("8", "٨"))
                .replaceAll("9", "٩")).replaceAll("0", "٠"));
        return newValue;
    }
}
