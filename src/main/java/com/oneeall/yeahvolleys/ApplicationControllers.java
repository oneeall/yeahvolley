package com.oneeall.yeahvolleys;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.oneeall.yeahvolleys.utils.OkHttp3Stack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oneal on 24/01/17.
 * danniel.alvianto@gmail.com
 */

@Deprecated
public class ApplicationControllers extends Application {
    /**
     * Log or request TAG
     */
    public static final String TAG = "com.oneeall.yeahvolleys.ApplicationControllers";

    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;

    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static ApplicationControllers sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the mContext;

        // initialize the singleton
        sInstance = this;
    }


    /* Setup Volley */
    /**
     * @return com.oneeall.yeahvolleys.ApplicationControllers singleton instance
     */
    public static synchronized ApplicationControllers getInstance() {
        return sInstance;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        // used OkHttp3Stack
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext() , new OkHttp3Stack());
        }
        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
            VolleyLog.d("Cancel Pending request to queue: %s", tag);

        }
    }

    public void  getHeaders(NetworkResponse networkResponse){
        Map<String, String> headers = new HashMap<>();
        headers.putAll(networkResponse.headers);
    }
    /* ---------------------------------------------------------------------------- */



}

