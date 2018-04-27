package com.nguyenthanhchung.carop2p;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;
import com.nguyenthanhchung.carop2p.handler.Sender;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Game extends AsyncTask<String,String,String>{

    private static final int TIME_OUT =500;

    private final String LOG_TAG="GameClass";
    private final int SERVER_PORT=8888;

    private boolean isServer;
    private InetAddress serverAddress;
    private InputStream inputStream;
    private BufferedReader reader;
    private PrintWriter writer;
    private ServerSocket server;
    private Socket socket;
    private Context context;
    private String message;
    private WiFiDirectActivity mActivity;


    @Override
    protected String doInBackground(String... params) {

        if(isServer){
            CreateSever();
        }else{
            CreateClient();
        }
        return null;
    }

    void CreateSever(){
        String msg="";
        try {
            server = new ServerSocket(SERVER_PORT);
            socket=server.accept();
            server.close();

            inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(socket.getOutputStream(), true);

            Log.d(LOG_TAG, "Server created on: " + SERVER_PORT);

            while(socket != null){
                msg = reader.readLine();
                if( msg==null || isCancelled()) break;
                Log.d(LOG_TAG,"Reading msg: "+ msg);
                publishProgress(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void CreateClient(){
        String msg="";
        try {
            Thread.sleep(1);

            socket = new Socket();
            socket.bind(null);
            socket.connect(new InetSocketAddress(serverAddress, SERVER_PORT), TIME_OUT);

            Log.d(LOG_TAG, "Connected to: " + serverAddress + " on port: " + SERVER_PORT);

            inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                msg = reader.readLine();
                if( msg == null || isCancelled() ) break;
                Log.d(LOG_TAG,"Reading msg: "+ msg);
                publishProgress(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d(LOG_TAG,"Progress update");
        //Nhận dữ liệu từ thiết bị khác và truyền cho mActivity để xử lí trên giao diện
        //Xử lí bằng gói bằng PackageData
        mActivity.handleIncoming(values[0]);
    }

    public Game(WiFiDirectActivity activity){
        this.context= activity.getApplicationContext();
        isServer=true;
        Log.d(LOG_TAG, "Game created on server side");
        mActivity = activity;
    }

    public Game(WiFiDirectActivity activity,InetAddress address){
        this.context = activity.getApplicationContext();
        this.serverAddress = address;
        isServer = false;
        Log.d(LOG_TAG,"Game created on client side");
        mActivity = activity;
    }

    public void sendMsg(String msg) {
        message = msg;
        new Thread(new Sender(msg,writer)).run();
    }

    public void run() {
        writer.println(message);
    }


}
