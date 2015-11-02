package com.evavzw.twentyonedayschallenge.challenges;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        private ImageView ivChallengeThumbnail;
        private TextView tvTitle;
        private TextView tvTagline;
        private ImageView ivStarOne;
        private ImageView ivStarTwo;

        public ViewHolder(View view) {
            this.view = view;
            ivChallengeThumbnail = (ImageView) this.view.findViewById(R.id.ivChallengeThumbmail);
            tvTitle = (TextView) this.view.findViewById(R.id.tvChallengeTitle);
            tvTagline = (TextView) this.view.findViewById(R.id.tvChallengeTagline);
            ivStarOne = (ImageView) this.view.findViewById(R.id.ivStarOne);
            ivStarTwo = (ImageView) this.view.findViewById(R.id.ivStarTwo);
            this.view.setTag(this);
        }

        public void updateDisplay(ChallengeCardItem item) {
            ivChallengeThumbnail.setImageResource(item.getImage());
            tvTitle.setText(item.getTitle());
            tvTagline.setText(item.getTagline());

            if (item.getStars() >= 1) {
                ivStarOne.setVisibility(View.VISIBLE);
                if (item.getStars() >= 2) {
                    ivStarTwo.setVisibility(View.VISIBLE);

                }

            }

        }
    }
}
