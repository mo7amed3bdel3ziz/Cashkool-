package com.example.n5arrb.UI.mainFragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.n5arrb.ViewModel.NotifayViewModel;
import com.example.n5arrb.adabters.AdebterNotifay;
import com.example.n5arrb.adabters.NotifayOnClic;
import com.example.n5arrb.databinding.FragmentNotifayBinding;
import com.example.n5arrb.network.TransactionsServiceRetrofit;
import com.example.n5arrb.pojo.NotifayModel;
import com.example.n5arrb.pojo.auth.LoginModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link notifayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notifayFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public notifayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notifayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static notifayFragment newInstance(String param1, String param2) {
        notifayFragment fragment = new notifayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
  //  ViewmodelT viewModels;
//
  //  FirebaseFirestore db = FirebaseFirestore.getInstance();
  FragmentNotifayBinding binding;
    NotifayViewModel viewModel;
    AdebterNotifay notifay;

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
        binding= FragmentNotifayBinding.inflate(getLayoutInflater(),container,false);
        viewModel=new ViewModelProvider(getActivity()).get(NotifayViewModel.class);
      //  viewModel
       // List<NotifayModel> notifayModels=null;
        binding.progressBar8.setVisibility(View.VISIBLE);
        binding.progressBar7.setVisibility(View.GONE);

        notifay= new AdebterNotifay(getActivity(), new NotifayOnClic() {
            @Override
            public void onClicNoifay(int pos, int id) {
             // binding.progressBar8.setVisibility(View.VISIBLE);
              binding.progressBar7.setVisibility(View.VISIBLE);
                 Observable GetTransactions= TransactionsServiceRetrofit.getInstans().
       getApiService().RejectTransaction(id)
                         . subscribeOn(Schedulers.io()).
                                 observeOn(AndroidSchedulers.mainThread());
                io.reactivex.rxjava3.core.Observer <LoginModel> listObserver=new io.reactivex.rxjava3.core.Observer<LoginModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginModel loginModel) {
                        notifay.notifyItemRemoved(pos);
                     //   notifay.notifyAll();
                               Toast.makeText(getActivity(), "You declined that Action ", Toast.LENGTH_SHORT).show();
                               Toast.makeText(getContext(), "You declined that Action ", Toast.LENGTH_SHORT).show();
                       // notifay.setModelArrayList(notifayModels);
                       // binding.recNotify.setAdapter(notifay);
                       // notifay.notifyDataSetChanged();
                        binding.progressBar7.setVisibility(View.GONE);
                       // notifay.notifyAll();


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                                Toast.makeText(getActivity(), "You declined that Action ", Toast.LENGTH_SHORT).show();
                        notifay.notifyItemRemoved(pos);
                                Toast.makeText(getActivity(), "You declined that Action ", Toast.LENGTH_SHORT).show();
                       binding.progressBar7.setVisibility(View.GONE);
                      //  notifay.notifyDataSetChanged();
                       // notifay.notifyAll();
                       // notifay.setModelArrayList(notifayModels);
                       // binding.recNotify.setAdapter(notifay);


                    }

                    @Override
                    public void onComplete() {

                    }
                };
                  GetTransactions.subscribe(listObserver);

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recNotify.setLayoutManager(linearLayoutManager);

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();



        String pf=  sharedPreferences.getString("phone","");
      //  Toast.makeText(getActivity(), pf, Toast.LENGTH_SHORT).show();
       // if (notifayModels!=null)
        viewModel.getNotifay(pf);
        viewModel.getNotifayLiveData().observe(getActivity(), new Observer<List<NotifayModel>>() {
       // viewModel.getNotifayLiveData().observe(getViewLifecycleOwner(), new Observer<List<NotifayModel>>() {
            @Override
            public void onChanged(List<NotifayModel> notifayModels) {

                if(notifayModels !=null){
                    binding.progressBar8.setVisibility(View.GONE);
                    notifay.setModelArrayList(notifayModels);
                    binding.recNotify.setAdapter(notifay);
                }
             //   Toast.makeText(getContext(), "sss", Toast.LENGTH_SHORT).show();
              //  Log.d("hag", s);
            //   try {
            //       String s=notifayModels.get(0).getPaymentDate();

            //      // Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(s);
            //       Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(s);


            //       SimpleDateFormat z=new SimpleDateFormat("yyyy-MM-dd");

            //       String a= z.format(date1);
            //       Log.d("hag", a+"  test");
            //   } catch (ParseException e) {
            //       e.printStackTrace();
            //   }

              // binding.progressBar8.setVisibility(View.GONE);
              // notifay.setModelArrayList(notifayModels);
              // binding.recNotify.setAdapter(notifay);


            }
        });



        // Inflate the layout for this fragment
      //  floatingActionadd=find

     // ArrayList<NotifayModel>list=new ArrayList<>();
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
     // list.add(new NotifayModel(true,"Hello"));
  //    AdebterNotifay   notifay= new AdebterNotifay( getActivity());


  //    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
  //    binding.recNotify.setLayoutManager(linearLayoutManager);
  //  //  notifay.setModelArrayList( list);
  //    binding.recNotify.setAdapter(notifay);
  //   viewModels = new ViewModelProvider(getActivity()).get(ViewmodelT.class);
  //  viewModels.getNotifay().observe(getActivity(), new Observer<ArrayList<NotifayModel>>() {
  //      @Override
  //      public void onChanged(ArrayList<NotifayModel> notifayModels) {
  //          notifay.setModelArrayList( notifayModels);
  //          binding.recNotify.setAdapter(notifay);
  //      }
  //  });






        return binding.getRoot();
    }
}