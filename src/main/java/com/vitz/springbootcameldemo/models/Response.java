package com.vitz.springbootcameldemo.models;

/**
 * Created by vsinha on 2/19/2018.
 */
public class Response<T> {
    private T responseObject;
    private String responseMessage;
    private String responseCode;

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseObject=" + responseObject +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseCode='" + responseCode + '\'' +
                '}';
    }
}
