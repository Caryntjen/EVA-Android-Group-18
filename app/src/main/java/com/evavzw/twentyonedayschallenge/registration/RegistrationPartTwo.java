package com.evavzw.twentyonedayschallenge.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.evavzw.twentyonedayschallenge.login.LoginActivity;


public class RegistrationPartTwo extends Fragment implements View.OnClickListener {

    // UI references.
    private static RadioGroup rgKindOfVegitarian;
    private static RadioButton rbOmnivorism;
    private static RadioButton rbPescetarianism;
    private static RadioButton rbVegetarianism;
    private static RadioButton rbParttimeVegetarianism;
    private static RadioButton rbVeganism;
    private static CheckBox cbNewsletter;
    private static Button btnComplete;
    private RegisterActivity _activity;

    private int diet;

    private boolean wantsNewsletter = true;

    public RegistrationPartTwo() {
    }

    private void updateRegistrationDataObject(){
        _activity.registration.sendNewsLetter = wantsNewsletter;
        _activity.registration.Difficulty = diet;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_part_two, container, false);

        rgKindOfVegitarian = (RadioGroup) view.findViewById(R.id.rgKindOfVegitarian);
        rgKindOfVegitarian.setOnClickListener(this);
        rbOmnivorism = (RadioButton) view.findViewById(R.id.rbOmnivorism);
        rbOmnivorism.setOnClickListener(this);
        rbPescetarianism = (RadioButton) view.findViewById(R.id.rbPescetarianism);
        rbPescetarianism.setOnClickListener(this);
        rbVegetarianism = (RadioButton) view.findViewById(R.id.rbVegetarianism);
        rbVegetarianism.setOnClickListener(this);
        rbParttimeVegetarianism = (RadioButton) view.findViewById(R.id.rbParttimeVegetarianism);
        rbParttimeVegetarianism.setOnClickListener(this);
        rbVeganism = (RadioButton) view.findViewById(R.id.rbVeganism);
        rbVeganism.setOnClickListener(this);

        cbNewsletter = (CheckBox) view.findViewById(R.id.cbNewsletter);
        cbNewsletter.setChecked(true);
        cbNewsletter.setOnClickListener(this);

        btnComplete = (Button) view.findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRegistrationDataObject();
                _activity.Register();
                Intent loginActivity = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        Button mFillButton = (Button) view.findViewById(R.id.fill_button);
        mFillButton.setFocusable(true);
        mFillButton.setFocusableInTouchMode(true);
        mFillButton.requestFocus();
        mFillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbVeganism.setChecked(true);
                diet = 5;
                cbNewsletter.setChecked(true);
                wantsNewsletter = true;

            }
        });
        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rbOmnivorism:
                //if (rbOmnivorism.isChecked())
                diet = 1;
                break;
            case R.id.rbPescetarianism:
                //if (rbPescetarianism.isChecked())
                diet = 2;
                break;
            case R.id.rbVegetarianism:
                //if (rbVegetarianism.isChecked())
                diet = 3;
                break;
            case R.id.rbParttimeVegetarianism:
                //if (rbParttimeVegetarianism.isChecked())
                diet = 4;
                break;
            case R.id.rbVeganism:
                //if (rbVeganism.isChecked())
                diet = 5;
                break;

            case R.id.cbNewsletter:
                //cbNewsletter.setChecked(!cbNewsletter.isChecked());
                wantsNewsletter = cbNewsletter.isChecked();
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;
        if (context instanceof Activity) {
            _activity = (RegisterActivity) context;
        }

    }

    public static RegistrationPartTwo newInstance() {
        return new RegistrationPartTwo();
    }
}
