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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The first part of the registration where the user needs to provide:
 * - The number of children they're having. (Maximum for males: 2000; for females: 69 - Source: https://en.wikipedia.org/wiki/List_of_people_with_the_most_children )
 * - Pick their birthday (Maximum in year for males: 116; for females: 122 - Source: https://en.wikipedia.org/wiki/Oldest_people, Minimum age is set tot 13 with Based on http://www.adweek.com/socialtimes/social-media-minimum-age/501920)
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

    //Minimum age to use the service is 13. Based on http://www.adweek.com/socialtimes/social-media-minimum-age/501920
    private static final int MIN_AGE = 13; //Jiroemon Kimura

    //Default constructor
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
        etChildren.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                else{
                    showKeyboard(v);
                }
            }
        });

        //Birthday Picker
        etBirthday = (EditText) view.findViewById(R.id.etBirthday);
        etBirthday.clearFocus();
        etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showBirthDatePicker();
                } else {
                    hideKeyboard(v);
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
        gender = FEMALE;
        rbEnglish.setChecked(true);
        language = ENGLISH;
        cbStudent.setChecked(isStudent);
        isStudent = false;

        //TODO: Need to button hide on release
        //Hidden button to fill in the default details.
        Button mFillButton = (Button) view.findViewById(R.id.fill_button);
        mFillButton.setFocusable(true);
        mFillButton.setFocusableInTouchMode(true);
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
        hideKeyboard(view);

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
                        //Validating the Birthday
                        String sBirthday = etBirthday.getText().toString();
                        //--- Empty field
                        if (TextUtils.isEmpty(sBirthday)) {
                            etBirthday.setError(getString(R.string.error_empty_birthday));
                            focusView = etBirthday;
                            cancel = true;
                        } else {
                            try {
                                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                formatter.setLenient(false);
                                Date inputDate = formatter.parse(sBirthday);
                                Calendar calendar = Calendar.getInstance();
                                Date todaysDate = calendar.getTime();
                                //Log.w("Todays Date", todaysDate.toString());
                                //Log.w("Input Date", inputDate.toString());

                                if (todaysDate.before(inputDate)) {
                                    //--- Date in the future is chosen
                                    etBirthday.setError(getString(R.string.error_future_date));
                                    focusView = etBirthday;
                                    cancel = true;

                                } else {
                                    Calendar minAge = (Calendar) calendar.clone();
                                    Calendar maxAgeMale = (Calendar) calendar.clone();
                                    Calendar maxAgeFemale = (Calendar) calendar.clone();

                                    minAge.add(Calendar.YEAR, -MIN_AGE);
                                    maxAgeMale.add(Calendar.YEAR, -MAX_YEAR_MALE);
                                    maxAgeFemale.add(Calendar.YEAR, -MAX_YEAR_FEMALE);

                                    //Log.w("Min Age Date", minAge.getTime().toString());
                                    //Log.w("Max Male Age Date", maxAgeMale.getTime().toString());
                                    //Log.w("Max Female Age Date", maxAgeFemale.getTime().toString());


                                    if (inputDate.after(minAge.getTime())) {
                                        //--- To young to use the app
                                        etBirthday.setError(getString(R.string.error_birthday_young));
                                        focusView = etBirthday;
                                        cancel = true;
                                    } else if (rbMale.isChecked() && inputDate.before(maxAgeMale.getTime())) {
                                        //--- To old as a male.
                                        etBirthday.setError(getString(R.string.error_birthday_old_male));
                                        focusView = etBirthday;
                                        cancel = true;
                                    } else if (rbFemale.isChecked() && inputDate.before(maxAgeFemale.getTime())) {
                                        //--- To old as a female.
                                        etBirthday.setError(getString(R.string.error_birthday_old_female));
                                        focusView = etBirthday;
                                        cancel = true;
                                    }
                                }
                            } catch (ParseException pe) {
                                //--- Invalid Pattern
                                etBirthday.setError(getString(R.string.error_invalid_date));
                                focusView = etBirthday;
                                cancel = true;
                            }
                        }


                        //--- Valid number, further validation
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
                    // There was an error. Focus on the element.
                    focusView.requestFocus();

                } else if (callback != null) {
                    //Error Messages, Keyboard & Icons need to disappear
                    etChildren.setError(null);
                    etBirthday.setError(null);

                    callback.onButtonClick(view);
                }
                break;

            default:
                break;
        }

    }

    /**
     * Hides the keyboard when the focus changes.
     *
     * @param view the view that needs the keyboard being hidden
     */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Shows the keyboard when the focus changes.
     *
     * @param view the view that needs the keyboard being hidden
     */
    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * This is a callback interface which informs the class RegisterActivity if a Butons is clicked
     */
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
