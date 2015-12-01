package com.evavzw.twentyonedayschallenge.registration;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.evavzw.twentyonedayschallenge.main.MainActivity;


/**
 * The second part of the registration where the user needs to provide:
 * - The kind of diet they're following, the default is Meat Eater.
 * - Check if they want the newsletter, default is checked.
 */

//TODO: Information should be save in database when registration is complete
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
    private static ScrollView svPartTwo;

    //Diet Abbreviations
    private static final String OMNIVORISM = "Omnivorism";
    private static final String PESCETARIANISM = "Pescetarianism";
    private static final String VEGETARIANSIM = "Vegetarianism";
    private static final String PARTTIME_VEGETARIANSIM = "Part-Time Vegetarianism";
    private static final String VEGANISM = "Veganism";

    //Provided information in registration form
    private String diet;
    private boolean wantsNewsletter;

    public RegistrationPartTwo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_part_two, container, false);


        //Kind of Diet
        rgKindOfVegitarian = (RadioGroup) view.findViewById(R.id.rgKindOfVegitarian);
        rgKindOfVegitarian.setOnClickListener(this);
        rgKindOfVegitarian.requestFocus();
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

        //Newsletter
        cbNewsletter = (CheckBox) view.findViewById(R.id.cbNewsletter);
        cbNewsletter.setOnClickListener(this);

        //Complete Button for registration.
        btnComplete = (Button) view.findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(this);

        //Defaults
        rbOmnivorism.setChecked(true);
        diet = OMNIVORISM;
        cbNewsletter.setChecked(true);
        wantsNewsletter = true;


        //TODO: Need to button hide on release
        //Hidden button to fill in the default details.
        Button mFillButton = (Button) view.findViewById(R.id.fill_button);
        mFillButton.setFocusable(true);
        mFillButton.setFocusableInTouchMode(true);
        mFillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbVeganism.setChecked(User.SEX.toBool());
                cbNewsletter.setChecked(User.LANGUAGE.toBool());
            }
        });

        return view;
    }

    /**
     * Handling the different click events for the UI references.
     *
     * @param view the view that being clicked on.
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rbOmnivorism:
                //if (rbOmnivorism.isChecked())
                diet = OMNIVORISM;
                break;
            case R.id.rbPescetarianism:
                //if (rbPescetarianism.isChecked())
                diet = PESCETARIANISM;
                break;
            case R.id.rbVegetarianism:
                //if (rbVegetarianism.isChecked())
                diet = VEGETARIANSIM;
                break;
            case R.id.rbParttimeVegetarianism:
                //if (rbParttimeVegetarianism.isChecked())
                diet = PARTTIME_VEGETARIANSIM;
                break;
            case R.id.rbVeganism:
                //if (rbVeganism.isChecked())
                diet = VEGANISM;
                break;

            case R.id.cbNewsletter:
                //cbNewsletter.setChecked(!cbNewsletter.isChecked());
                wantsNewsletter = cbNewsletter.isChecked();
                break;

            case R.id.btnComplete:
                Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivity);
                break;

            default:
                break;
        }

    }

    /**
     * Private instance method of the Registration
     */
    private static RegistrationPartTwo newInstance() {
        return new RegistrationPartTwo();
    }
}
