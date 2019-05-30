package com.example.trawell.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trawell.Activities.Anasayfa;
import com.example.trawell.Activities.MainActivity;
import com.example.trawell.Activities.SifreDegistirActivity;
import com.example.trawell.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;


public class AyarlarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SwitchCompat switchNotif;
    boolean stateSwitch1;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;

    private TextView userName, userMail, userPhone, userTW, userFB;
    private ImageView userImage;

    private String mParam1;
    private String mParam2;
    private Button btnHesapSil;
    private Button sifreDegis;
    private ProgressBar progressBar;

    private TextView kullaniciMail;


    private OnFragmentInteractionListener mListener;

    public AyarlarFragment() {


        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AyarlarFragment newInstance(String param1, String param2) {
        AyarlarFragment fragment = new AyarlarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        //kullaniciMail.setText(user.getEmail());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_ayarlar, container, false);

        btnHesapSil = (Button) view.findViewById(R.id.btn_hesap_sil);

        sifreDegis =(Button) view.findViewById(R.id.btn_sifre_deg);

        progressBar = (ProgressBar) view.findViewById(R.id.hesapSilPB);

        sifreDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SifreDegistirActivity.class);
                startActivity(i);
            }
        });

        btnHesapSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle("Emin Misiniz");
                builder.setMessage("Hesabınız silinmek üzere. Eğer hesabınızı silersenz hesap içinde bulunan her şey silinir. Uygulamayı tekrar kullanabilmek için yeni hesap açmak zorunda kalırsınız");
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()){
                                    Toast.makeText(getActivity(),"Hesap Silindi",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getActivity(), MainActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });

                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        switchNotif = (SwitchCompat) view.findViewById(R.id.sw_bildirim);
        return view;



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
