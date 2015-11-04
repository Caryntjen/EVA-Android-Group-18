package com.evavzw.twentyonedayschallenge.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;

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
        tvEmail.setText(User.EMAIL.toString());

        tvBirthday = (TextView) activity.findViewById(R.id.tvBirthday);
        tvBirthday.setText(User.BIRTHDAY.toString());

        tvSex = (TextView) activity.findViewById(R.id.tvSex);
        tvSex.setText(User.SEX.toString());

        tvLanguage = (TextView) activity.findViewById(R.id.tvLanguage);
        tvLanguage.setText(User.LANGUAGE.toString());

        tvStudent = (TextView) activity.findViewById(R.id.tvStudent);
        tvStudent.setText(User.STUDENT.toString());

        tvChildren = (TextView) activity.findViewById(R.id.tvChildren);
        tvChildren.setText(User.CHILDREN.toString());

        tvNewsletter = (TextView) activity.findViewById(R.id.tvNewsletter);
        tvNewsletter.setText(User.NEWSLETTER.toString());

        tvDiet = (TextView) activity.findViewById(R.id.tvDiet);
        tvDiet.setText(User.DIET.toString());

        btnInvite = (Button) activity.findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"hogent@evavzw.be"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Come join the challenge");
                intent.putExtra(Intent.EXTRA_TEXT, "Go check this new app out!");
                startActivity(Intent.createChooser(intent, ""));
    */
            }
        });
    }
}
