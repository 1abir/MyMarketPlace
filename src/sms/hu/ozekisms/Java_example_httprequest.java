package sms.hu.ozekisms;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Java_example_httprequest implements Runnable{
    private String messager;

    public Java_example_httprequest(String message) {
        this.messager = message;
        Thread thread=new Thread(this);
        thread.start();
    }

    public void run(){
        try{
            String recipient = "+8801766905524";
            String message = "Hello World";
            String username = "smppuser";
            String password = "LsdQH9dY";
            String originator = "+8801515261139";
            String requestUrl  = "http://192.168.0.5:9500/api?action=sendmessage&" +
                    "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8") +
                    "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
                    "&messagetype=SMS:TEXT" +
                    "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
                    "&originator=" + URLEncoder.encode(originator, "UTF-8") +
                    "&serviceprovider=GSMModem0" +
                    "&responseformat=html";



            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();

            System.out.println(uc.getResponseMessage());

            uc.disconnect();

        } catch(Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

}
