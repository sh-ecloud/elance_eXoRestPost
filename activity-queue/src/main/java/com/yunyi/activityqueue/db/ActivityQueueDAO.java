package com.yunyi.activityqueue.db;

import com.yunyi.activityqueue.bean.Activity;

import java.sql.Connection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qizhou
 * Date: 7/19/14
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityQueueDAO {


    public List<Activity> getActivities(Connection conn)  throws Exception;

    public void deleteActivityByID(Connection conn, int ID)  throws Exception;


}
