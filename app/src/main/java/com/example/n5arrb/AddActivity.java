package com.example.n5arrb;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import com.example.n5arrb.UI.mainFragment.MainActivity5;
import com.example.n5arrb.databinding.ActivityAddBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.AddTransactions;
import com.example.n5arrb.pojo.ResponseTransactionModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddActivity extends AppCompatActivity {
    private static int REQUEST_CODE_STT = 1;
    ActivityAddBinding binding;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener1;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    public static final Integer RecordAudioRequestCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        Toast.makeText(this, "qqqq", Toast.LENGTH_SHORT).show();
        binding.btnStt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddActivity.this, "", Toast.LENGTH_SHORT).show();

                SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(AddActivity.this);

                //   Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //   speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //   speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                //         Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");

                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");


                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(AddActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //   Intent sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                //   sttIntent.putExtra(
                //           RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                //           RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                //   );
                //   sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                //   sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")

                //   try {
                //       startActivityForResult(sttIntent, REQUEST_CODE_STT);
                //   } catch (ActivityNotFoundException e) {
                //       e.printStackTrace()
                //   }
            }
        });


        binding.progressBar.setVisibility(View.GONE);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String num = i.getStringExtra("num");
        String state = i.getStringExtra("state");
        int sata = Integer.valueOf(state);
        Toast.makeText(this, state + "000000", Toast.LENGTH_SHORT).show();
        binding.nameCust.setText(name);
        binding.PhoneCus.setText(num);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Log.d("testsss0", sata+"");
        // Date d=new Date();


        // String.valueOf(year);
        String tim = DateFormat.getDateInstance().format(c.getTime());
        binding.TransactionDate.setText(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));

        //getDatePicker().setMaxDate(c.getTimeInMillis());
        // DialogFragment newFragment = new DatePickerFragment();
        // newFragment.show(getSupportFragmentManager(), "datePicker");


        binding.CollectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                //getDatePicker().setMaxDate(c.getTimeInMillis());
                // DialogFragment newFragment = new DatePickerFragment();
                // newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String date = i2 + "/" + i1 + "/" + i;
                binding.CollectDate.setText(date);
            }
        };
        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String date = i2 + "/" + i1 + "/" + i;
                binding.TransactionDate.setText(date);
            }
        };
        binding.btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("statss", sata + "");
                if (isvaled() == true && sata == 1) {
                    Log.d("sata2", sata+"");

                    binding.progressBar.setVisibility(View.VISIBLE);
                    String non = binding.PhoneCus.getText().toString().replace(" ", "");
                    // Intent.Va(non)
                    //  String s=  non.trim();
                    Log.d("testsss0", non);

                    String numpp = "+2" + non;
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();

                    Log.d("testsss1", numpp.toString());
                    Log.d("testsss2", numpp.trim().toString());
                    String pf = sharedPreferences.getString("phone", "");
                    Toast.makeText(AddActivity.this, pf, Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddActivity.this, numpp, Toast.LENGTH_SHORT).show();
                    Date d = new Date();
                    AddTransactions add = new AddTransactions(Double.valueOf(binding.editTextNumber.getText().toString()), d, d, binding.typTransaction.getText().toString(), name, numpp.trim(), pf);

                    Log.d("testsssq", numpp.trim().toString() + "..." + pf + "q");
                    TransactionsServiceRetrofit.getInstans().getApiService().AddTransaction(add).enqueue(new Callback<ResponseTransactionModel>() {
                        @Override
                        public void onResponse(Call<ResponseTransactionModel> call, Response<ResponseTransactionModel> response) {
                            binding.progressBar.setVisibility(View.VISIBLE);
                            Log.d("ekodpfekf", response.body().toString());

                            Intent i = new Intent(AddActivity.this, MainActivity5.class);
                            startActivity(i);
                            finish();
                            //  Toast.makeText(AddActivity.this, "نجح", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<ResponseTransactionModel> call, Throwable t) {
                            //   Log.d("hagerr", t.getLocalizedMessage());

                            //   Toast.makeText(AddActivity.this, "Response", Toast.LENGTH_SHORT).show();
                            binding.progressBar.setVisibility(View.VISIBLE);
                            Intent i = new Intent(AddActivity.this, MainActivity5.class);
                            startActivity(i);
                            finish();

                        }

                    });

                } else if (isvaled() == true && sata == 2) {


                    binding.progressBar.setVisibility(View.VISIBLE);
                    String non = binding.PhoneCus.getText().toString().replace(" ", "");
                    // Intent.Va(non)
                    //  String s=  non.trim();
                    Log.d("sata2", sata+"");

                    String numpp = "+2" + non;

                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();

                    Log.d("testsss1", numpp.toString());
                    Log.d("testsss2", numpp.trim().toString());
                    String pf = sharedPreferences.getString("phone", "");
                    Toast.makeText(AddActivity.this, pf, Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddActivity.this, numpp, Toast.LENGTH_SHORT).show();
                    Date d = new Date();
                    AddTransactions add = new AddTransactions(Double.valueOf(binding.editTextNumber.getText().toString()), d, d, binding.typTransaction.getText().toString(), name, pf, numpp.trim());
                    Log.d("testsssq", numpp.trim().toString() + "..." + pf + "s");

                    Log.d("sata2", pf+"--"+ numpp.trim());

                    TransactionsServiceRetrofit.getInstans().getApiService().AddTransaction(add).enqueue(new Callback<ResponseTransactionModel>() {
                        @Override
                        public void onResponse(Call<ResponseTransactionModel> call, Response<ResponseTransactionModel> response) {
                            Log.d("ekodpfekf", response.body().toString());
                            Log.d("sata2", "ok");

                            // Toast.makeText(AddActivity.this, "نجح", Toast.LENGTH_SHORT).show();
                            binding.progressBar.setVisibility(View.VISIBLE);
                            Intent i = new Intent(AddActivity.this, MainActivity5.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResponseTransactionModel> call, Throwable t) {
                            Log.d("ekodpfekf",t.getMessage());
                            Log.d("sata2", "no");

                            // Log.d("hagerr", t.getLocalizedMessage());
                            // Toast.makeText(AddActivity.this, "Response", Toast.LENGTH_SHORT).show();
                            binding.progressBar.setVisibility(View.VISIBLE);
                            Intent i = new Intent(AddActivity.this, MainActivity5.class);
                            startActivity(i);
                            finish();

                        }

                    });

                } else {
                    Toast.makeText(AddActivity.this, "no", Toast.LENGTH_SHORT).show();
                }

//                binding.button3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(AddActivity.this, "21", Toast.LENGTH_SHORT).show();
//
//                        Log.d("iosdjfsdiofjp","dddd");
//                    }
//                });

                //  isUserValid(non);
                // final int[] x = new int[1];

                // int ss = x[0];
                // Toast.makeText(AddActivity.this, String.valueOf(ss), Toast.LENGTH_SHORT).show();

                //
                //
                //  int []s=  isUserValid(non);
                //  int ss= s [0];

                //  Toast.makeText(AddActivity.this,String.valueOf(ss) , Toast.LENGTH_SHORT).show();
                //   if (s[0] ==1){
                //          Toast.makeText(AddActivity.this, " is emp", Toast.LENGTH_SHORT).show();

                //   }else {
                //       Toast.makeText(AddActivity.this, " not emp", Toast.LENGTH_SHORT).show();

                //   }
                ;
                //  sendTransaction();
                //    startActivity(new Intent(AddActivity.this,MainActivity5.class));

            }
        });


    }


    public Boolean isvaled() {

        if (binding.editTextNumber.getText().toString().isEmpty()) {
            binding.editTextNumber.setError("AddCredit");
            return false;
        }

        if (binding.CollectDate.getText().toString().isEmpty()) {
            binding.CollectDate.setError("Enter Collect Date");
            return false;
        }


        if (binding.typTransaction.getText().toString().isEmpty()) {
            binding.typTransaction.setError("Enter Description");
            return false;
        }


        {
            return true;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                binding.typTransaction.setText(Objects.requireNonNull(result).get(0));
                String a = Objects.requireNonNull(result).get(0) + ".";
                int i;
                String str1 = "";
                String str0 = "";
                String str2 = "";

                ArrayList<String> strings = new ArrayList<>();

                for (i = 0; i < a.length(); i++) {
                    char c = a.charAt(i);
                    if ('0' <= c && c <= '9') {
                        str1 = str1 + c;
                    } else {
                        if (str1==""){

                        }else {
                            strings.add(str1);
                        }

                        //( 'a' <= c && c <= 'z' )
                        Log.d("dwojfe1", str1 + "-");
                        str1 = "";

                        str2 = str2 + c;
                    }
                }
                Log.d("dwojfe1", "s");

                String[] ints = new String[strings.size()];
                for (i = 0; i < strings.size(); i++) {
                    Log.d("dwojfe1", strings.get(i) + i);
                    ints[i] = strings.get(i);
                }
                binding.editTextNumber.setText(str1);
                Log.d("dwojfe1", str1 + "ز");
                dilog4(ints);

                //   Log.d("dwojfe1", str1);
                //   try {
                //       String s = ".";
                //       for (i = 0; i <strings.size(); i++){
                //           if (strings.get(i).isEmpty()){

                //           }else {
                //               s=  strings.get(i);
                //           }
                //       }
                //       Log.d("dwojfe1", s);
                //   }catch ( Exception s){
                //       Log.d("dwojfe1", s.getMessage().toString()+"...");

                //   }

// setup //e alert builder
                //     AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                //     builder.setTitle("Choose an animal");

// add a //st
                //     String[] animals ={"dd"};
                //     builder.setItems(animals, new DialogInterface.OnClickListener() {
                //         @Override
                //         public void onClick(DialogInterface dialog, int which) {

                //             switch (which) {
                //                 case 0: // horse
                //                 case 1: // cow
                //                 case 2: // camel
                //                 case 3: // sheep
                //                 case 4: // goat
                //             }
                //         }
                //     });

// create//nd show the alert dialog
                //     AlertDialog dialog = builder.create();
                //     dialog.show();

            }
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }


//    fun onActivityResult(int requestCode , resultCode: Int, data: Intent?) {
//       super.onActivityResult(requestCode, resultCode, data)
//       when (requestCode) {
//           REQUEST_CODE_STT -> {
//               if (resultCode == Activity.RESULT_OK && data != null) {
//                   val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//                   result?.let {
//                       val recognizedText = it[0]
//                       et_text_input.setText(recognizedText)
//                   }
//               }
//           }
//       }
//   }

//   override fun onPause() {
//       textToSpeechEngine.stop()
//       super.onPause()
//   }


    //   @Override
//   protected void onDestroy() {
//       textToSpeechEngine.shutdown();
//       super.onDestroy();
//   }
    public void dilog(String[] s) {
        // setup the alert builder

// add a checkbox list
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
            builder.setTitle("اختار قيمه او مجموعه قيم");

            String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
            boolean[] checkedItems = new boolean[s.length];
            for (int i = 0; i < s.length; i++) {
                checkedItems[i]=false;
            }

            ArrayList<String>aa=new ArrayList<>();
            builder.setMultiChoiceItems(s, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    String x="";
                    if ( checkedItems[which]==true){
                        x=s[which];
                        aa.add(x);
                        Log.d("sssssssddfcxs",which+""+x+"--");

                    }
                    else {
                        //if ( checkedItems[which]==false){
                        //   x=s[which];

                        //  try {
                        //   Log.d("sssssssddfcxs",which+""+x+"--1");
                        aa.remove(which-1);


                        //   }catch (Exception e){
                        //   Log.d("sssssssddfcxs",e.getMessage());
                        //  }

                    }
                    //  checkedItems[which]=isChecked;

                    // user checked or unchecked a box
                    Toast.makeText(AddActivity.this, which+"", Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(AddActivity.this, dialog., Toast.LENGTH_SHORT).show();

                }
            });
            //   builder.setSingleChoiceItems(s, 1, new DialogInterface.OnClickListener() {
            //       @Override
            //       public void onClick(DialogInterface dialogInterface, int i) {

            //       }
            //   });
            //  builder.setSingleChoiceItems(animals, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            //      @Override
            //      public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            //          // user checked or unchecked a box
            //      }
            //  });// add OK and Cancel buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(AddActivity.this, which+"", Toast.LENGTH_SHORT).show();
                    // user clicked OK
                    int nums = 0;
                    for (int i = 0; i < aa.size(); i++) {
                        // Toast.makeText(AddActivity.this, aa.get(i), Toast.LENGTH_SHORT).show();
                        nums=nums+Integer.parseInt(aa.get(i).trim());
                    }
                    binding.    editTextNumber.setText(nums+"");
                }
            });
            builder.setNegativeButton("Cancel", null);

// create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (Exception e) {
            Log.d("dwojfe1", e.getMessage());


        }


    }

    public void dilog2() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddActivity.this);
        //   builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Select One Name:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Hardik");
        arrayAdapter.add("Archit");
        arrayAdapter.add("Jignesh");
        arrayAdapter.add("Umang");
        arrayAdapter.add("Gatti");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(AddActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }
    public void dilog4(String[] s){
        boolean[] checkeditems;
        checkeditems =new boolean[s.length];
        ArrayList<Integer> mUserItems =new ArrayList<>();
        androidx.appcompat.app.AlertDialog.Builder mBuilder =new androidx.appcompat.app.AlertDialog.Builder(AddActivity.this);
        mBuilder.setTitle("اختار قيمه او مجموعه قيم");
        mBuilder.setMultiChoiceItems(s, checkeditems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {

                if (isChecked){
                    //   if (!mUserItems.contains(position)){
                    mUserItems.add( position);

                    Collections.sort(mUserItems);
                }else {
                    mUserItems.remove(Integer.valueOf(position));

                }
                //  }
            }
        });
        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item ="";
                int zx=0;
                for (int i=0; i<mUserItems.size(); i++)
                {
                    Log.d("item",item+"0");
                    item
                            =
                            //   item +
                            s[mUserItems.get(i)];
                    //  Log.d("item",item+"1");
                    zx+=Integer.parseInt(item.trim());
                    //  if (i !=mUserItems.size() -1)
                    //  {
                    //    //  item="";
                    //        Log.d("item",item+"2");
                    //      zx+=Integer.parseInt(item.trim());
                    //        Log.d("item",item+"3");
                    //     // item =item +",";
                    // }

                }
                binding.  editTextNumber.setText(zx+"");
            }
        });
        // mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
        //     @Override
        //     public void onClick(DialogInterface dialog, int which) {
        //         String item ="";
        //         int zx=0;
        //         for (int i=0; i<mUserItems.size(); i++)
        //         {
        //             item =item +s[mUserItems.get(i)];
        //             if (i !=mUserItems.size() -1)
        //             {
        //                item +=item +", ";
        //               // item +=item ;
        //               // zx+=Integer.parseInt(item);
        //               //  Log.d("item",item);
        //             }
//
        //         }
        //          binding.    editTextNumber.setText(item);
        //        // binding.    editTextNumber.setText(zx);
        //     }
        // });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //  mBuilder.setNeutralButton("cler", new DialogInterface.OnClickListener() {
        //      @Override
        //      public void onClick(DialogInterface dialogInterface, int which) {
        //          for (int i =0 ;i<checkeditems.length ;i++){
        //              checkeditems[i] =false;
        //              mUserItems.clear();
        //              mItemSelected.setText("");
        //          }
        //      }
        //  });
        androidx.appcompat.app.AlertDialog mDialog =mBuilder.create();
        mDialog.show();
    }
}