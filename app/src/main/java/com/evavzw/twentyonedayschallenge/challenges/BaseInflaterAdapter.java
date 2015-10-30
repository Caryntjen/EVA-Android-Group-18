package com.evavzw.twentyonedayschallenge.challenges;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseInflaterAdapter<T> extends BaseAdapter {
    private List<T> challenges = new ArrayList<T>();
    private IAdapterViewInflater<T> cvInflater;

    public BaseInflaterAdapter(IAdapterViewInflater<T> cvInflater) {
        this.cvInflater = cvInflater;
    }

    public void addItem(T item, boolean notifyChange) {
        challenges.add(item);

        if (notifyChange)
            notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return challenges.size();
    }

    @Override
    public Object getItem(int pos) {
        return getTItem(pos);
    }

    public T getTItem(int pos) {
        return challenges.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(cvInflater != null)
            return cvInflater.inflate(this, position, convertView, parent);
        else return null;
    }
}
