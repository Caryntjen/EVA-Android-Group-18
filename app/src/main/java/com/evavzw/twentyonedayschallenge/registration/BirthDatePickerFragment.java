package com.evavzw.twentyonedayschallenge.registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * A DialogFragment to pick the BirthDate.
 */

public class BirthDatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener ondateSet;

    public BirthDatePickerFragment() {}

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), ondateSet, year, month, day);
        DatePicker dp = dpd.getDatePicker();

        //Setting the maximum date for the Date picker.
        dp.setMaxDate(c.getTimeInMillis());
        return dpd;
    }
}