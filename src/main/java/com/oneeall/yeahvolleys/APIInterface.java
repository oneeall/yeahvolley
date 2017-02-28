package com.oneeall.yeahvolleys;

/**
 * Created by oneal on 24/01/17.
 * danniel.alvianto@gmail.com
 */

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.oneeall.yeahvolleys.utils.VolleyMultipartRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by oneal on 07/01/17.
 * danniel.alvianto@gmail.com
 */

public interface APIInterface {

    void Success (JSONObject response);

    void Error (VolleyError error);

    Map<String, String> SetHeaders(Map<String, String> headers);

    interface APIInterfaceMultipart {
        void Success (NetworkResponse response);

        void Error (VolleyError error);

        Map<String, String> setHeaders(Map<String,String> headers);

        Map<String, String> setParams(Map<String, String> params);

        Map<String, VolleyMultipartRequest.DataPart> setByteData(Map<String, VolleyMultipartRequest.DataPart> params);
    }
}
