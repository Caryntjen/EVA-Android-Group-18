package com.evavzw.twentyonedayschallenge.tabfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;

/*
    The AccountFragment class will display the information of the account of the user.
    There's also the possibility to invite a friend through e-mail.
*/
public class AccountFragment extends Fragment implements ITabFragment {

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

    private static final String EMAILTYPE = "message/rfc822";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.fragment_activity_account, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Email Information
        tvEmail = (TextView) activity.findViewById(R.id.tvEmail);

        //Birthday Information
        tvBirthday = (TextView) activity.findViewById(R.id.tvBirthday);

        //Gender Information
        tvSex = (TextView) activity.findViewById(R.id.tvSex);

        //Language Information
        tvLanguage = (TextView) activity.findViewById(R.id.tvLanguage);

        //Student Information
        tvStudent = (TextView) activity.findViewById(R.id.tvStudent);

        //Number of Children Information
        tvChildren = (TextView) activity.findViewById(R.id.tvChildren);

        //Subscribed to newsletter Information
        tvNewsletter = (TextView) activity.findViewById(R.id.tvNewsletter);

        //Kind of Diet Information
        tvDiet = (TextView) activity.findViewById(R.id.tvDiet);

        //Button to invite others through email if the intent can not be started a short Toast is made that no email client is found.
        btnInvite = (Button) activity.findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(EMAILTYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{tvEmail.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.overview_invite_email_title));
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.overview_invite_email_body));
                try {
                    startActivity(Intent.createChooser(emailIntent, getString(R.string.overview_invite_email_chooser)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(activity, getString(R.string.overview_invite_email_notfound), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragment();
    }

    /*
        When the fragment is swiped or clicked to, the information needs to be updated, which happens here.
     */
    @Override
    public void updateFragment() {
        //TODO: Load information into TextViews
        tvEmail.setText(User.EMAIL.toString());
        tvBirthday.setText(User.BIRTHDAY.toString());
        tvSex.setText(User.SEX.toString());
        tvLanguage.setText(User.LANGUAGE.toString());
        tvNewsletter.setText(User.NEWSLETTER.toString());
        tvStudent.setText(User.STUDENT.toString());
        tvChildren.setText(User.CHILDREN.toString());
        tvDiet.setText(User.DIET.toString());

    }
}
