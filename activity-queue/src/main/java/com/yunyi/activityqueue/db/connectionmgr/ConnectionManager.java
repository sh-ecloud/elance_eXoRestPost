package com.yunyi.activityqueue.db.connectionmgr;

import com.yunyi.activityqueue.configure.Configure;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created with IntelliJ IDEA.
 * User: qizhou
 * Date: 7/19/14
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionManager {

    private static ConnectionManager manager = new ConnectionManager();

    private ConnectionManager()
    {

    }

    public static ConnectionManager getInstance() {
        return manager;
    }

    public Connection getConnection() throws Exception{

        //jdbc.cas.url=jdbc:sqlserver://poetictest.asiapacific.hpqcorp.net:1433; DatabaseName=SEMS
        //jdbc.cas.username=sems
        //jdbc.cas.password=sems

        String dburl = Configure.getInstance().get("dbUrl");
        String dbuser =Configure.getInstance().get("dbUser");
        String dbPassword =Configure.getInstance().get("dbPassword");

       // String url = "jdbc:sqlserver://localhost; DatabaseName=yunyi";

        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";


        Class.forName(driver);//.newInstance();
        Connection conn = DriverManager.getConnection(dburl, dbuser, dbPassword);
        conn.setAutoCommit(true);
        return conn;
    }



}
