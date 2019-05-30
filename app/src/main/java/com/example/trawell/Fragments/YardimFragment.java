package com.example.trawell.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.trawell.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YardimFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YardimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YardimFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private YardimFragment yardimFragment;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public YardimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YardimFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YardimFragment newInstance(String param1, String param2) {
        YardimFragment fragment = new YardimFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        final EditText your_name, your_mail, your_subject, your_message;


        // Inflate the layout for this fragment
        View view_yardim =  inflater.inflate(R.layout.fragment_yardim, container, false);

        your_name = (EditText) view_yardim.findViewById(R.id.txt_contact_name);
        your_mail = (EditText) view_yardim.findViewById(R.id.txt_contact_mail);
        your_subject = (EditText) view_yardim.findViewById(R.id.txt_contact_subject);
        your_message = (EditText) view_yardim.findViewById(R.id.txt_contact_message);


        Button btnMesajGonder = (Button) view_yardim.findViewById(R.id.btn_contact_send);

        btnMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = your_name.getText().toString();
                String email = your_mail.getText().toString();
                String subject = your_subject.getText().toString();
                String message = your_message.getText().toString();
                if (TextUtils.isEmpty(name)){
                    your_name.setError("İsminizi Giriniz.");
                    your_name.requestFocus();
                    return;
                }
                Boolean onError = false;
                if (!isValidEmail(email)){
                    onError = true;
                    your_mail.setError("Geçersiz Mail");
                    return;
                }
                if (TextUtils.isEmpty(subject)){
                    your_subject.setError("Konu Giriniz.");
                    your_subject.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(subject)) {
                    your_message.setError("Mesajınızı Giriniz.");
                    your_message.requestFocus();
                    return;
                }
                Intent sendMail = new Intent(Intent.ACTION_SEND);

                sendMail.setType("plain/text");
                sendMail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"apptrawell@gmail.com"});
                sendMail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                sendMail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "İsim:"+name+'\n'+"Email:"+email+'\n'+"Mesaj:"+'\n'+message);

                startActivity(Intent.createChooser(sendMail, "Mail gönder..."));
            }
        });
        return view_yardim;
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
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
