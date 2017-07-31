package com.android.summer.csula.foodvoter.yelpApi;


import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestAccessTokenTask {

    /* Base URL */
    private static final String YELP_TOKEN_URL = "https://api.yelp.com/oauth2/token";

    /* Parameters */
    private static final String CLIENT_ID_PARAM = "client_id";
    private static final String CLIENT_SECRET_PARAM = "client_secret";

    /* Values */
    private static final String CLIENT_ID_VALUE = "XSix_2kuH-XJruY2UqtlFQ";
    private static final String CLIENT_SECRET_VALUE = "Xkkl35Rak4a9lkKBFYf82w0USlNnIhR4BptRpfnTfA5NUH1fICO5X65OskKxhXnu";


    /* Token response fields */
    private static final String ACCESS_TOKEN = "access_token";
    private static final String TOKEN_TYPE = "token_type";
    private static final String EXPIRES_IN = "expires_in";


    public static YelpAccessToken execute() throws IOException, JSONException {
        URL tokenUrl = buildTokenUrl();
        String jsonResponse = NetworkUtils.getResponseFromHttpUrl(tokenUrl, NetworkUtils.POST_REQUEST);
        JSONObject json = new JSONObject(jsonResponse);

        String accessToken = json.getString(ACCESS_TOKEN);
        String tokenType = json.getString(TOKEN_TYPE);
        int expiresIn = json.getInt(EXPIRES_IN);

        return new YelpAccessToken(accessToken, tokenType, expiresIn);
    }

    private static URL buildTokenUrl() throws MalformedURLException {
        Uri uri = Uri.parse(YELP_TOKEN_URL).buildUpon()
            .appendQueryParameter(CLIENT_ID_PARAM, CLIENT_ID_VALUE)
            .appendQueryParameter(CLIENT_SECRET_PARAM, CLIENT_SECRET_VALUE)
            .build();

        return new URL(uri.toString());
    }
}
