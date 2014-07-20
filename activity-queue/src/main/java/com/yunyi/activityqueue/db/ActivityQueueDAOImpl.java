package com.yunyi.activityqueue.db;

import com.yunyi.activityqueue.bean.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qizhou
 * Date: 7/19/14
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityQueueDAOImpl implements ActivityQueueDAO {

    @Override
    public List<Activity> getActivities(Connection conn) throws Exception{
         Statement stmt = null;
         ResultSet rset = null;

        try{
            stmt =  conn.createStatement();
            String sql = "select a.id, b.Createdby, c.Text2, b.Notes0 from ACTIVITY_QUEUE a inner join contacts b on a.ID= b.ID inner join ADDRESSES c on b.SUPERID = c.ID";
            rset = stmt.executeQuery(sql);
            List<Activity> activities =  new ArrayList();

            while(rset.next())
            {
                Activity activity = new Activity();
                activity.setText2(rset.getString("TEXT2"));
                activity.setCreatedBy(rset.getString("Createdby"));
                activity.setNotes0(rset.getString("Notes0"));
                activity.setId(rset.getInt("ID"));

                activities.add(activity);
            }

            return activities;

        }  finally {
             if(rset != null)
             {
                 rset.close();
             }

             if(stmt != null)
             {
                 stmt.close();
             }
        }

    }

    @Override
    public void deleteActivityByID(Connection conn, int ID)  throws Exception{
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try{
            stmt =  conn.prepareStatement("delete from ACTIVITY_QUEUE where ID=?");
            stmt.setInt(1, ID);
            stmt.executeUpdate();


            return;

        }  finally {
            if(rset != null)
            {
                rset.close();
            }

            if(stmt != null)
            {
                stmt.close();
            }
        }


    }
}
