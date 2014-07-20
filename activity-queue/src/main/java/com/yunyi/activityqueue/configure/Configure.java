package com.yunyi.activityqueue.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: qizhou
 * Date: 7/19/14
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Configure {

    private Properties properties = new Properties();

    private static Configure configure = new Configure();

    private Configure () {

//        InputStream stream =  Configure.class.getClassLoader().getResourceAsStream("configuration.properties");
        InputStream stream =  Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");

        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Configure getInstance()
    {
         return configure;
    }

    public  String get(String key)
    {
        return (String) properties.get(key);
    }
}
