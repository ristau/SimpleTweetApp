package com.codepath.apps.mysimpletweetapp.models;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;


//"user": {
//        "name": "OAuth Dancer",
//        "profile_sidebar_fill_color": "DDEEF6",
//        "profile_background_tile": true,
//        "profile_sidebar_border_color": "C0DEED",
//        "profile_image_url": "http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
//        "created_at": "Wed Mar 03 19:37:35 +0000 2010",
//        "location": "San Francisco, CA",
//        "follow_request_sent": false,
//        "id_str": "119476949",
//        "is_translator": false,
//        "profile_link_color": "0084B4",
//        "entities": {
//        "url": {
//        "urls": [
//        {
//        "expanded_url": null,
//        "url": "http://bit.ly/oauth-dancer",
//        "indices": [
//        0,
//        26
//        ],
//        "display_url": null
//        }
//        ]
//        },
//        "description": null
//        },
//        "default_profile": false,
//        "url": "http://bit.ly/oauth-dancer",
//        "contributors_enabled": false,
//        "favourites_count": 7,
//        "utc_offset": null,
//        "profile_image_url_https": "https://si0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
//        "id": 119476949,
//        "listed_count": 1,
//        "profile_use_background_image": true,
//        "profile_text_color": "333333",
//        "followers_count": 28,
//        "lang": "en",
//        "protected": false,
//        "geo_enabled": true,
//        "notifications": false,
//        "description": "",
//        "profile_background_color": "C0DEED",
//        "verified": false,
//        "time_zone": null,
//        "profile_background_image_url_https": "https://si0.twimg.com/profile_background_images/80151733/oauth-dance.png",
//        "statuses_count": 166,
//        "profile_background_image_url": "http://a0.twimg.com/profile_background_images/80151733/oauth-dance.png",
//        "default_profile_image": false,
//        "friends_count": 14,
//        "following": false,
//        "show_all_inline_media": false,
//        "screen_name": "oauth_dancer"
//        },



public class User implements Parcelable {

    // list attributes
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;


    // getters
    public  String getName() {
        return name;
    }

    public long   getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    // deserialize the user json -> User
    public static User fromJSON(JSONObject json){

        User u = new User();
        // extract and fill the values from the json

        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return a user
        return u;
    }

    // parcelable methods
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeLong(uid);
        out.writeString(screenName);
        out.writeString(profileImageUrl);
    }

    private User(){

    }
    private User(Parcel in) {
        name = in.readString();
        uid = in.readLong();
        screenName = in.readString();
        profileImageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


}
