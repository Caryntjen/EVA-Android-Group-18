package com.evavzw.twentyonedayschallenge.firstrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.login.LoginActivity;

public class FirstRunPartTwo extends Fragment implements View.OnClickListener  {
    private static Button btnBeginChallenge;
    public FirstRunPartTwo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstrun_part_two, container, false);

        btnBeginChallenge = (Button) view.findViewById(R.id.btnBeginChallenge);
        btnBeginChallenge.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnBeginChallenge:
                Intent loginActivity = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginActivity);
                break;

            default:
                break;
        }

    }
}