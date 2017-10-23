package com.gaparmar.traq;

/**
 * Created by rtmurase on 10/21/17.
 */


import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class SmsSender{

    public SmsSender( String phone, String message ){
        try{
            String url2 = "http://default-environment.bmicg4bdyt.us-east-2.elasticbeanstalk.com/sms";
            String input = "test";
            String urlParameters = "phone=" + phone + "&haha=" + message;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = "http://default-environment.bmicg4bdyt.us-east-2.elasticbeanstalk.com/sms";
            URL    url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            } catch (Exception e){
                e.printStackTrace();
            }
            int code = conn.getResponseCode();
            System.out.println(code);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public SmsSender( String phone, String name, String Location, String time ){
        try{
            String urlParameters = "phone=" + phone + "&device=" + name + "&location="+Location+"&time="+time+"&secret=" + "";
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = "http://default-environment.bmicg4bdyt.us-east-2.elasticbeanstalk.com/sms";
            URL    url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            } catch (Exception e){
                e.printStackTrace();
            }
            int code = conn.getResponseCode();
            System.out.println(code);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public SmsSender (String s){
        try{
            String urlParameters = "secret="+s+"&phone="+DataHolder.getInstance().getPhoneNum();

            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = "http://default-environment.bmicg4bdyt.us-east-2.elasticbeanstalk.com/sms";
            URL    url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            } catch (Exception e){
                e.printStackTrace();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
