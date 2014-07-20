package com.yunyi.activityqueue.tool;

import com.yunyi.activityqueue.bean.Activity;
import com.yunyi.activityqueue.configure.Configure;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: qizhou
 * Date: 7/19/14
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonConvertor {

    public static String toString(Activity activity)
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("message", activity.getCreatedBy() + " - " + activity.getText2() + " - " + activity.getNotes0());

        String spaceName = Configure.getInstance().get("spaceName");

        jsonObject.put("spaceName", spaceName);
        return jsonObject.toString();
    }
}
