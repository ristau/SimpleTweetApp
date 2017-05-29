package com.codepath.apps.mysimpletweetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweetapp.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

// Taking the Tweet Objects and turning them into Views that will be displayed in the list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets){
        super(context, 0, tweets);
    }

    // override and setup custom template
    // ViewHolder pattern - review this 

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Get the tweet
        Tweet tweet = getItem(position);

//      // 2. Find or inflate the template
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
//      // 3. Find the subViews to fill the data in the template
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvCreatedAt = (TextView) convertView.findViewById(R.id.tvCreatedAt);
        TextView tvScreenName = (TextView) convertView.findViewById(R.id.tvScreenName);

//      // 4. Populate data into the subviews
        tvUserName.setText(tweet.getUser().getName());
        tvBody.setText(tweet.getBody());
        tvCreatedAt.setText(tweet.getCreatedAt());
        tvScreenName.setText("@" + tweet.getUser().getScreenName());
        ivProfileImage.setImageResource(android.R.color.transparent);  // clear the old image for a recycled view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        // 5. Return the view to be inserted into the list

        return convertView;
    }


}
