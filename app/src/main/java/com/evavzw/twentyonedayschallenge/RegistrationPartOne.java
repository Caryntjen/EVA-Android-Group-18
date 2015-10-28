package com.evavzw.twentyonedayschallenge;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;


public class RegistrationPartOne extends Fragment implements View.OnClickListener {

    // UI references.
    private static EditText etBirthday;
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
    private String brithday;

    private boolean isStudent = false;

    public RegistrationPartOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_part_one, container, false);

        etBirthday = (EditText) view.findViewById(R.id.birthday);
        etBirthday.setEnabled(false);
        etBirthday.setOnClickListener(this);

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
        btnNext.setOnClickListener(this);
        return view;
    }

    private void showBirthDatePicker() {
        BirthDatePickerFragment date = new BirthDatePickerFragment();

        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Birthday");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            brithday = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);

            etBirthday.setText(brithday);
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.birthday:
                showBirthDatePicker();


            case R.id.rbEnglish:
                if (rbEnglish.isChecked())
                    language = "EN";
                break;
            case R.id.rbFrench:
                if (rbFrench.isChecked())
                    language = "FR";
                break;


            case R.id.rbMale:
                if (rbMale.isChecked())
                    sex = "M";
                break;
            case R.id.rbFemale:
                if (rbFemale.isChecked())
                    sex = "F";
                break;

            case R.id.cbStudent:
                if (cbStudent.isChecked()) {
                    isStudent = true;
                } else isStudent = false;
                break;

            case R.id.btnNext:
                //TODO: Need some validation
                break;

            default:
                break;
        }

    }
}
