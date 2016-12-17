package com.github.sasd97.utils;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;

/**
 * Created by Alexander Dadukin on 11/29/2016.
 */

public class RequestUtils {

    private RequestUtils() {}

    public static void getFacebookUser(String token, Callback<JsonNode> callback) {
        String url = String.format("https://graph.facebook.com/me?access_token=%s&fields=first_name", token);
        Unirest.get(url).asJsonAsync(callback);
    }
}
