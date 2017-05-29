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


//    private void setupFloatingLabelError() {
//        final TextInputLayout floatingUsernameLabel = (TextInputLayout) findViewById(R.id.etCompose_text_input_layout);
//        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
//            // ...
//            @Override
//            public void onTextChanged(CharSequence text, int start, int count, int after) {
//                if (text.length() > 0 && text.length() <= 10) {
//                    floatingUsernameLabel.setError(getString(R.string.maxChar));
//                    floatingUsernameLabel.setErrorEnabled(true);
//                } else {
//                    floatingUsernameLabel.setErrorEnabled(false);
//                }
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }

}
