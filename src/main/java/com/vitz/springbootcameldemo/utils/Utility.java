package com.vitz.springbootcameldemo.utils;

import com.vitz.springbootcameldemo.models.Response;

/**
 * Created by vsinha on 2/19/2018.
 */
public class Utility {

    public static <T> Response<T> createResponse(T obj, String message, String code){
        Response<T> response = new Response<T>();
        response.setResponseObject(obj);
        response.setResponseCode(code);
        response.setResponseMessage(message);
        return response;
    }
}
