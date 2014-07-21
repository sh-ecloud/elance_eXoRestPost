package com.yunyi.activityqueue.monitor;

import com.yunyi.activityqueue.bean.Activity;
import com.yunyi.activityqueue.configure.Configure;
import com.yunyi.activityqueue.db.ActivityQueueDAO;
import com.yunyi.activityqueue.db.ActivityQueueDAOImpl;
import com.yunyi.activityqueue.db.connectionmgr.ConnectionManager;
import com.yunyi.activityqueue.reportor.RestfulReportor;
import java.sql.Connection;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: qizhou Date: 7/19/14 Time: 12:46 PM To change this template use
 * File | Settings | File Templates.
 */
public class MonitorService implements Runnable {

    private static MonitorService service = new MonitorService();
    private ActivityQueueDAO activityQueueDao = new ActivityQueueDAOImpl();

    private MonitorService() {
    }

    public static MonitorService getInstance() {
        return service;
    }

    public void startMonitor() {
        Thread thd = new Thread(this);
        thd.start();
    }

    @Override
    public void run() {

        System.out.println("Executing Monitor");

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Start processing ...");
            try {

                // get connection for each scheduler call
                Connection conn = ConnectionManager.getInstance().getConnection();

                // retrieve all activities
                List<Activity> activities = activityQueueDao.getActivities(conn);
                if (activities == null || activities.size() == 0) {
                    System.out.println("no record, end the job.");
                }
                for (Activity activity : activities) {
                    // report to restful server
                    System.out.println("report Activity...." + activity.getId());
                    RestfulReportor.getInstance().reportActivities(activity);

                    activityQueueDao.deleteActivityByID(conn, activity.getId());
                }

                // close the connection.
                conn.close();

                // conn.commit();

                System.out.println("Stop processing ...");
                // now wai for a while
                String interval = Configure.getInstance().get("interval");
                int intervalMinute = Integer.valueOf(interval) * 60 * 1000;

                Thread.sleep(intervalMinute);

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }

    }
}
