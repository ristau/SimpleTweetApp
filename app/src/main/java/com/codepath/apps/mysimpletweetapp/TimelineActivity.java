package com.codepath.apps.mysimpletweetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mysimpletweetapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Find the Listview
        lvTweets = (ListView) findViewById(R.id.lvTweets);

        // Create the arraylist (data source)
        tweets = new ArrayList<>();

        // Construct the adapter from the data source
        aTweets = new TweetsArrayAdapter(this, tweets);

        // Connect adapter to the list view
        lvTweets.setAdapter(aTweets);

        // get the client
        client = TwitterApplication.getRestClient(); // singleton client
        populateTimeLine();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    // Send an API request to get the timeline json
    // Fill the listview by creating the tweet objects from the json
    private void populateTimeLine() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {

            // SUCCESS

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                super.onSuccess(statusCode, headers, json);
                Log.d("DEBUG", json.toString());

                // JSON HERE

                // DESERIALIZE JSON
                // CREATE MODELS AND ADD THEM TO THE ADAPTER
                // LOAD THE MODEL DATA INTO THE LISTVIEW

                aTweets.addAll(Tweet.fromJSONArray(json));
                Log.d("DEBUG", aTweets.toString());

            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miCompose:
                  Log.d("TEST", "TAPPED ON COMPOSE");
                composeMessage();
                return true;
            case R.id.miProfile:
                Log.d("TEST", "TAPPED ON PROFILE");
                showProfileView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void composeMessage() {

        Intent i = new Intent(this, ComposeActivity.class);
        i.putExtra("tweetText", 2);
        startActivityForResult(i, REQUEST_CODE);

    }

    public void showProfileView(){
        Toast.makeText(this, "Hee hee", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            Tweet newTweet = data.getParcelableExtra(ComposeActivity.TWEET_INTENT_KEY);
             Log.d("DEBUG INTENT", newTweet.getBody().toString());
             tweets.add(0, newTweet);
             aTweets.notifyDataSetChanged();

        }
    }


    }
