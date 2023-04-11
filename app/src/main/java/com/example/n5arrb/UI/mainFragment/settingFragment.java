package com.example.n5arrb.UI.mainFragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.n5arrb.MainActivity4;
import com.example.n5arrb.UI.authentication.oTP.SendOTPActivity;
import com.example.n5arrb.databinding.FragmentSettingBinding;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public settingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingFragment newInstance(String param1, String param2) {
        settingFragment fragment = new settingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
   // ViewmodelT viewModels;
    FragmentSettingBinding binding;
   // FloatingActionButton floatingActionadd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentSettingBinding.inflate(getLayoutInflater(),container,false);

        binding.cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor edit=sharedPreferences.edit();


                edit.putString("phone",   null);
                // edit.putString("image", encodImag);
                edit.apply();
                Intent i =new Intent(getActivity(), SendOTPActivity.class);
                startActivity(i);
               // getActivity().getFragmentManager().popBackStack();
                getActivity().onBackPressed();
            }
        });

  //   binding.BTNEn.setOnClickListener(new View.OnClickListener() {
  //       @Override
  //       public void onClick(View view) {
  //           setLocale("en");
  //       }
  //   });

  //   binding.BTNAr.setOnClickListener(new View.OnClickListener() {
  //       @Override
  //       public void onClick(View view) {
  //           setLocale("ar");
  //       }
  //   });

        return binding.getRoot();
    }

    @SuppressWarnings("deprecation")
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), MainActivity5.class);
        startActivity(refresh);
    }

}