package com.codepath.apps.mysimpletweetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.mysimpletweetapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    private TwitterClient client;
    private String tweet;
    public static String TWEET_INTENT_KEY = "tweet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApplication.getRestClient();

        Toast.makeText(this, "Composing tweet here", Toast.LENGTH_SHORT).show();
    }





//        MoTwitterApplication.getRestClient().postNewTweet(newTweet.text, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                int previousTweetIndex = mTweets.indexOf(newTweet);
//                mTweets.set(previousTweetIndex, Tweet.fromJSON(response));
//                mTimelineAdapter.notifyItemChanged(previousTweetIndex);
//            }
//        });


    public void onSubmit(View v) {

        EditText etCompose = (EditText) findViewById(R.id.etCompose);
        String text = etCompose.getText().toString();
        client.sendTweet(text, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Intent data = new Intent();
                Log.d("DEBUG", "SENT TWEET");
                Tweet newTweet = Tweet.fromJSON(response);
                Log.d("DEBUG", newTweet.getBody().toString());
                data.putExtra(TWEET_INTENT_KEY, newTweet);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }

        //    Intent data = new Intent();
//        data.putExtra("tweetText", etCompose.getText().toString());
//        setResult(RESULT_OK, data);
//        finish();



//        client.sendTweet(text, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                tweets.add(0, Tweet.fromJSON(response));
//                aTweets.notifyDataSetChanged();
//                Log.d("DEBUG", "SENT TWEET");
//            }
//        });



}
