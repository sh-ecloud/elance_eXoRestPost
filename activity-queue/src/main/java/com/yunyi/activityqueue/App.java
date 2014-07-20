package com.yunyi.activityqueue;

import com.yunyi.activityqueue.monitor.MonitorService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //read configuration from -D options
        //start runing the monitor
        System.out.println("App Started.... ");

        MonitorService.getInstance().startMonitor();

    }
}
