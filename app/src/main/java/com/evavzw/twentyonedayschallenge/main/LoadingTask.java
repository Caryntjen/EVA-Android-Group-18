package com.evavzw.twentyonedayschallenge.main;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;


public class LoadingTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressBar pb;

    public LoadingTask(ProgressBar pb) {
        this.pb = pb;
    }

    public void setProgressBar(ProgressBar pb) {
        this.pb = pb;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return true;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
    }
    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        pb.setVisibility(View.GONE);
    }
}
