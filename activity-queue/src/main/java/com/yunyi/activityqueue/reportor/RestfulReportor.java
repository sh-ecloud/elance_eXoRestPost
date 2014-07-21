package com.yunyi.activityqueue.reportor;

import com.yunyi.activityqueue.bean.Activity;
import com.yunyi.activityqueue.configure.Configure;
import sun.misc.BASE64Encoder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA. User: qizhou Date: 7/19/14 Time: 12:59 PM To change this template use
 * File | Settings | File Templates.
 */
public class RestfulReportor {

    private static RestfulReportor reportor = new RestfulReportor();
    private String encodedAuthorization = null;

    private RestfulReportor() {

        BASE64Encoder enc = new sun.misc.BASE64Encoder();
        String userName = Configure.getInstance().get("eXoUser");
        String passwd = Configure.getInstance().get("eXoPasswd");
        String userpassword = userName + ":" + passwd;
        encodedAuthorization = enc.encode(userpassword.getBytes());
    }

    public static RestfulReportor getInstance() {
        return reportor;
    }

    // send restful request
    public void reportActivities(Activity activity) throws Exception {

        HttpURLConnection httpConn = null;
        OutputStream out = null;

        try {

            String reportURL = Configure.getInstance().get("eXoServerUrl") + "rest/private/activity/space";

            URL url = new URL(reportURL);
            // System.out.println(Thread.currentThread().getName()+"-----------------begin to connect-------------------");
            // long startTime = System.currentTimeMillis();
            httpConn = (HttpURLConnection) url.openConnection();
            // long endTime = System.currentTimeMillis();
            // System.out.println(Thread.currentThread().getName()+"------------------Connected-----------------"+
            // (endTime-startTime)/1000);
            // httpConn.setRequestProperty("Content-Type",
            // "application/xstream_xml");
            httpConn.setRequestProperty("Content-Type", "application/json");

            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("Authorization", "Basic " + encodedAuthorization);

            httpConn.setDoOutput(true);
            out = httpConn.getOutputStream();
            String activityJsonStr = new JSONObject(activity).toString();

            out.write(activityJsonStr.getBytes("UTF-8"));
            out.flush();

            if (httpConn.getResponseCode() >= 400) {
                if (httpConn != null) {
                    InputStreamReader errStream = new InputStreamReader(httpConn.getErrorStream());
                    BufferedReader br = new BufferedReader(errStream);
                    String read = br.readLine();

                    while (read != null) {
                        System.out.println(read);
                        read = br.readLine();
                    }
                }

                throw new Exception("HTTP ERROR:" + httpConn.getResponseCode());
            }

        } finally {
            closeOutputStream(out);
            // endTime = System.currentTimeMillis();
            // System.out.println(Thread.currentThread().getName()+
            // "end to close output stream" + (endTime-startTime)/1000);

            // System.out.println(Thread.currentThread().getName()+
            // "begin to close connection");
            // startTime = System.currentTimeMillis();
            closeHttpConnection(httpConn);
        }

    }

    private void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (Exception ex) {
        }

    }

    private void closeHttpConnection(HttpURLConnection httpConn) {
        try {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        } catch (Exception ex) {
        }

    }

    private void closeOutputStream(OutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
        } catch (Exception ex) {
        }

    }

    public static void main(String[] args) {
        Activity activity = new Activity();
        activity.setId(100);
        activity.setText2("ABC");
        JSONObject obj = new JSONObject(activity);
        System.out.println(obj.toString());
    }
}
