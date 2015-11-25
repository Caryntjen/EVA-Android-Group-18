package com.evavzw.twentyonedayschallenge.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;

/**
 * The first part of the registration where the user needs to provide:
 * - The number of children they're having. (Maximum for males: 2000; for females: 69 - Source: https://en.wikipedia.org/wiki/List_of_people_with_the_most_children
 * - Pick their birthday (Maximum in year for males: 116; for females: 122 - Source: https://en.wikipedia.org/wiki/Oldest_people
 * - Choose the Gender, with default Gender being female
 * - Language, with Default Language being English
 * - Check if they're a student, default is not checked.
 */

//TODO: Information should be save in database when registration is complete

public class RegistrationPartOne extends Fragment implements View.OnClickListener {

    // UI references.
    private static EditText etBirthday;
    private static EditText etChildren;
    private static RadioGroup rgGender;
    private static RadioButton rbMale;
    private static RadioButton rbFemale;
    private static RadioGroup rgLanguage;
    private static RadioButton rbFrench;
    private static RadioButton rbEnglish;
    private static RadioButton rbDutch;
    private static CheckBox cbStudent;
    private static Button btnNext;

    //Provided information in registration form
    private String gender;
    private String language;
    private String birthday;
    private int children;
    private boolean isStudent;

    //Language Abbreviations
    private static final String ENGLISH = "EN";
    private static final String FRENCH = "FR";
    private static final String DUTCH = "NL";

    //Gender Abbreviations
    private static final String MALE = "M";
    private static final String FEMALE = "F";

    //BirthDay Dialog
    private static final String BIRTHDAYDIALOG_TAG = "Birthday";

    //Number of Maximum Children - Source: https://en.wikipedia.org/wiki/List_of_people_with_the_most_children
    private static final int MAX_CHILDREN_MALE = 2000; //Genghis Khan
    private static final int MAX_CHILDREN_FEMALE = 69; //Mrs. Vassilyev

    //Oldest People in Years - Source: https://en.wikipedia.org/wiki/Oldest_people
    private static final int MAX_YEAR_FEMALE = 122; //Jeanne Calment
    private static final int MAX_YEAR_MALE = 116; //Jiroemon Kimura


    public RegistrationPartOne() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_part_one, container, false);

        //Number of Children
        etChildren = (EditText) view.findViewById(R.id.etNumberOfChildren);

        //Birthday Picker
        etBirthday = (EditText) view.findViewById(R.id.etBirthday);
        etBirthday.clearFocus();
        etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showBirthDatePicker();
                }
            }
        });
        etBirthday.setInputType(InputType.TYPE_NULL);

        //Gender Information
        rgGender = (RadioGroup) view.findViewById(R.id.rgGender);
        rgGender.setOnClickListener(this);
        rbMale = (RadioButton) view.findViewById(R.id.rbMale);
        rbMale.setOnClickListener(this);
        rbFemale = (RadioButton) view.findViewById(R.id.rbFemale);
        rbFemale.setOnClickListener(this);


        //Language Information
        rgLanguage = (RadioGroup) view.findViewById(R.id.rgLanguage);
        rgLanguage.setOnClickListener(this);
        rbFrench = (RadioButton) view.findViewById(R.id.rbFrench);
        rbFrench.setOnClickListener(this);
        rbEnglish = (RadioButton) view.findViewById(R.id.rbEnglish);
        rbEnglish.setOnClickListener(this);
        rbDutch = (RadioButton) view.findViewById(R.id.rbDutch);
        rbDutch.setOnClickListener(this);

        //Student Information
        cbStudent = (CheckBox) view.findViewById(R.id.cbStudent);
        cbStudent.setOnClickListener(this);

        //Next Button for registration.
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        //Defaults
        rbFemale.setChecked(true);
        rbEnglish.setChecked(true);
        isStudent = false;
        cbStudent.setChecked(isStudent);


        //TODO: Need to button hide on release
        //Hidden button to fill in the default details.
        Button mFillButton = (Button) view.findViewById(R.id.fill_button);
        mFillButton.setFocusable(true);
        mFillButton.setFocusableInTouchMode(true);
        mFillButton.requestFocus();
        mFillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBirthday.setText(User.BIRTHDAY.toString());
                etChildren.setText(User.CHILDREN.toString());
                rbFemale.setChecked(User.SEX.toBool());
                rbEnglish.setChecked(User.LANGUAGE.toBool());
                cbStudent.setChecked(User.STUDENT.toBool());
            }
        });
        return view;
    }

    /**
     * Method to show the Birthday Picker.
     */
    private void showBirthDatePicker() {
        BirthDatePickerFragment date = new BirthDatePickerFragment();
        date.setCancelable(true);
        date.setCallBack(ondate);
        date.show(getFragmentManager(), BIRTHDAYDIALOG_TAG);

    }


    /**
     * Setting the OnDateSetListener for the Date Picker Dialog.
     */
    private DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            birthday = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);

            etBirthday.setText(birthday);
        }
    };

    /**
     * Handling the different click events for the UI references.
     *
     * @param view the view that being clicked on.
     */

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            //--- LANGUAGE ---//
            //Clicked on English
            case R.id.rbEnglish:
                //if (rbEnglish.isChecked())
                language = ENGLISH;
                break;
            //Clicked on French
            case R.id.rbFrench:
                //if (rbFrench.isChecked())
                language = FRENCH;
                break;
            //Clicked on Dutch
            case R.id.rbDutch:
                //if (rbFrench.isChecked())
                language = DUTCH;
                break;

            //--- GENDER ---//
            //Clicked on Male
            case R.id.rbMale:
                //if (rbMale.isChecked())
                gender = MALE;
                break;
            //Clicked on Female
            case R.id.rbFemale:
                //if (rbFemale.isChecked())
                gender = FEMALE;
                break;

            //--- STUDENT ---//
            //Clicked on Male
            case R.id.cbStudent:
                //cbStudent.setChecked(!cbStudent.isChecked());
                isStudent = cbStudent.isChecked();
                break;

            //--- NEXT REGISTRATION PART ---//
            //Clicked on Next Button
            case R.id.btnNext:
                View focusView = null;
                boolean cancel = false;

                //Validating if Number Of Children is filled in and a positive number or zero and is not tied to exceeding the maximum.
                String sChildren = etChildren.getText().toString();
                //--- Empty field
                if (TextUtils.isEmpty(sChildren)) {
                    etChildren.setError(getString(R.string.error_empty_children));
                    focusView = etChildren;
                    cancel = true;
                } else {
                    try {
                        //--- Valid number
                        children = Integer.parseInt(sChildren);
                        if (children < 0) {
                            //--- Negative number
                            etChildren.setError(getString(R.string.error_negative_children));
                            focusView = etChildren;
                            cancel = true;
                        } else if (rbFemale.isChecked() && children > MAX_CHILDREN_FEMALE) {
                            //--- To many children as a female
                            etChildren.setError(getString(R.string.error_female_children));
                            focusView = etChildren;
                            cancel = true;
                        } else if (rbMale.isChecked() && children > MAX_CHILDREN_MALE) {
                            //--- To many children as a male
                            etChildren.setError(getString(R.string.error_male_children));
                            focusView = etChildren;
                            cancel = true;
                        }
                    } catch (NumberFormatException nfe) {
                        //--- Invalid Number
                        etChildren.setError(getString(R.string.error_invalid_children));
                        focusView = etChildren;
                        cancel = true;

                    }
                }
                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else if (callback != null) {
                    callback.onButtonClick(view);
                }
                //TODO: Need some validation for the Birthday
                break;

            default:
                break;
        }

    }

    public interface Callback {
        public void onButtonClick(View button);
    }

    private Callback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity) {
            callback = (Callback) context;
        }

    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }
}
