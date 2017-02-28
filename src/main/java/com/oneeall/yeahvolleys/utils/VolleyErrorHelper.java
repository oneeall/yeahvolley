package com.oneeall.yeahvolleys.utils;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.oneeall.yeahvolleys.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oneal on 24/01/17.
 * danniel.alvianto@gmail.com
 */

public class VolleyErrorHelper {
    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     * @param context
     * @return
     */
    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return "Time out";
        }
        else if (isServerProblem(error)) {
            return handleServerError(error, context);
        }
        else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_internet);
        }
        return "Try Again";
    }

    /**
     * Determines whether the error is related to network
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }
    /**
     * Determines whether the error is related to server
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock message or to
     * show a message retrieved from the server.
     *
     * @param err
     * @param context
     * @return
     */
    private static String handleServerError(Object err, Context context) {
        Map<String, String> headers = new HashMap<>();
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        //init headers for get informations response
        headers.putAll(response.headers);


        if (response != null) {
            switch (response.statusCode) {
                case 403:
                    return getErrorResponseMessage(response);
                case 404:
                    return getErrorResponseMessage(response);
                case 402:
                    return getErrorResponseMessage(response);
                case 401:
                    return getErrorResponseMessage(response);
                default:
                    return context.getResources().getString(R.string.generic_server_down);
            }
        }
        return context.getResources().getString(R.string.generic_error);
    }

    public static String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }

    private static String getErrorResponseMessage(NetworkResponse response){
        String msg = "";
        msg = new String(response.data);
        msg = trimMessage(msg, "message");
        return msg;
    }

}
