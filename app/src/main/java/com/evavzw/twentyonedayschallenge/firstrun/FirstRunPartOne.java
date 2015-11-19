package com.evavzw.twentyonedayschallenge.firstrun;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evavzw.twentyonedayschallenge.R;

public class FirstRunPartOne extends Fragment {
    private static Button btnNext;

    public FirstRunPartOne() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstrun_part_one, container, false);

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