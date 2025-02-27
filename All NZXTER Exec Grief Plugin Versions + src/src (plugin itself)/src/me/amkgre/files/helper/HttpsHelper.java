package me.amkgre.files.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsHelper implements Runnable {

    String link;
    File out;

    public HttpsHelper(String x, File out){
        this.link = x;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            HttpsURLConnection http = (HttpsURLConnection)url.openConnection();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(this.out);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = in.read(buffer, 0, 1024)) >= 0){
                bout.write(buffer, 0, read);
            }
            bout.close();
            in.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}