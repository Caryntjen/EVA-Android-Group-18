package com.evavzw.twentyonedayschallenge.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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


public class RegistrationPartOne extends Fragment implements View.OnClickListener {

    // UI references.
    private static EditText etBirthday;
    private static EditText etChildren;
    private static RadioGroup rgSex;
    private static RadioButton rbMale;
    private static RadioButton rbFemale;
    private static RadioGroup rgLanguage;
    private static RadioButton rbFrench;
    private static RadioButton rbEnglish;
    private static CheckBox cbStudent;
    private static Button btnNext;

    private String sex;
    private String language;
    private String birthday;
    private int children;

    private boolean isStudent = false;

    public RegistrationPartOne() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_part_one, container, false);

        etBirthday = (EditText) view.findViewById(R.id.etBirthday);
        etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showBirthDatePicker();
                }
            }
        });

        etChildren = (EditText) view.findViewById(R.id.etNumberOfChildren);

        rgSex = (RadioGroup) view.findViewById(R.id.rgSex);
        rgSex.setOnClickListener(this);
        rbMale = (RadioButton) view.findViewById(R.id.rbMale);
        rbMale.setOnClickListener(this);
        rbFemale = (RadioButton) view.findViewById(R.id.rbFemale);
        rbFemale.setOnClickListener(this);


        rgLanguage = (RadioGroup) view.findViewById(R.id.rgLanguage);
        rgLanguage.setOnClickListener(this);
        rbFrench = (RadioButton) view.findViewById(R.id.rbFrench);
        rbFrench.setOnClickListener(this);
        rbEnglish = (RadioButton) view.findViewById(R.id.rbEnglish);
        rbEnglish.setOnClickListener(this);

        cbStudent = (CheckBox) view.findViewById(R.id.cbStudent);
        cbStudent.setOnClickListener(this);

        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (callback != null) {
                    callback.onButtonClick(view);
                }
            }
        });
        return view;
    }

    private void showBirthDatePicker() {
        BirthDatePickerFragment date = new BirthDatePickerFragment();
        date.setCancelable(true);
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Birthday");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            birthday = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);

            etBirthday.setText(birthday);
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rbEnglish:
                //if (rbEnglish.isChecked())
                language = "EN";
                break;
            case R.id.rbFrench:
                //if (rbFrench.isChecked())
                language = "FR";
                break;


            case R.id.rbMale:
                //if (rbMale.isChecked())
                sex = "M";
                break;
            case R.id.rbFemale:
                //if (rbFemale.isChecked())
                sex = "F";
                break;

            case R.id.cbStudent:
                //cbStudent.setChecked(!cbStudent.isChecked());
                isStudent = cbStudent.isChecked();
                break;

            //case R.id.btnNext:
            //TODO: Need some validation
            //break;

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
