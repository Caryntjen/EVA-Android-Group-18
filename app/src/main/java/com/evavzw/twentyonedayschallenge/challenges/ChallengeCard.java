package com.evavzw.twentyonedayschallenge.challenges;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;

public class ChallengeCard implements IAdapterViewInflater<ChallengeCardItem> {
    @Override
    public View inflate(final BaseInflaterAdapter<ChallengeCardItem> adapter, final int pos, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.challenge_card, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ChallengeCardItem item = adapter.getTItem(pos);
        holder.updateDisplay(item);

        return convertView;
    }

    private static class ViewHolder {
        private View view;
        private TextView tvTitle;
        private TextView tvTagline;

        public ViewHolder(View view) {
            this.view = view;
            tvTitle = (TextView) this.view.findViewById(R.id.tvChallengeTitle);
            tvTagline = (TextView) this.view.findViewById(R.id.tvChallengeTagline);
            this.view.setTag(this);
        }

        public void updateDisplay(ChallengeCardItem item) {
            tvTitle.setText(item.getTitle());
            tvTagline.setText(item.getTagline());
        }
    }
}
