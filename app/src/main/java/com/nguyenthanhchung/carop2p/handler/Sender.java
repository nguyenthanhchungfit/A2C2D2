package com.nguyenthanhchung.carop2p.handler;

import android.util.Log;

import java.io.PrintWriter;

public class Sender implements Runnable {

    private String message;
    private PrintWriter writer;

    public Sender(String msg,PrintWriter wrtr){
        message=msg;
        writer=wrtr;
    }
    @Override
    public void run() {
        if(writer!=null) {
            writer.println(message);
        }else{
            Log.d("Sender", "Writer null");
        }
    }
}
