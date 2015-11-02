package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;

public class AccountAcitivy extends Fragment {

    private FragmentActivity activity;

    private static TextView tvEmail;
    private static TextView tvStudent;
    private static TextView tvSex;
    private static TextView tvBirthday;
    private static TextView tvDiet;
    private static TextView tvChildren;
    private static TextView tvLanguage;
    private static TextView tvNewsletter;
    private static Button btnInvite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.activity_account, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvEmail = (TextView) activity.findViewById(R.id.tvEmail);
        tvEmail.setText("firstname.lastname.number@student.hogent.be");

        tvBirthday = (TextView) activity.findViewById(R.id.tvBirthday);
        tvBirthday.setText("20-01-1968");

        tvSex = (TextView) activity.findViewById(R.id.tvSex);
        tvSex.setText("Female");

        tvLanguage = (TextView) activity.findViewById(R.id.tvLanguage);
        tvLanguage.setText("English");

        tvStudent = (TextView) activity.findViewById(R.id.tvStudent);
        tvStudent.setText("No");

        tvChildren = (TextView) activity.findViewById(R.id.tvChildren);
        tvChildren.setText("2");

        tvNewsletter = (TextView) activity.findViewById(R.id.tvNewsletter);
        tvNewsletter.setText("Yes");

        tvDiet = (TextView) activity.findViewById(R.id.tvDiet);
        tvDiet.setText("Veganism");

        btnInvite = (Button) activity.findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
