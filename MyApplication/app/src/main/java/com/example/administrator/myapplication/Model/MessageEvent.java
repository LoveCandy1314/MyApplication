package com.example.administrator.myapplication.Model;

/**
 * Created by Administrator on 2017/7/17.
 */

public class MessageEvent {
    private  String message;
    public  MessageEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
