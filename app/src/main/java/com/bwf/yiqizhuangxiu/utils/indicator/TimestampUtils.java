package com.bwf.yiqizhuangxiu.utils.indicator;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.application.App;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administor on 2016/11/29.
 */

public class TimestampUtils {
    public static String millisecondToTimestamp(long milliseconed) {
        String result = "";
        long time = Calendar.getInstance().getTimeInMillis() / 1000 - milliseconed;
        if (time / 60 < 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = new Date(milliseconed);
            result = format.format(d);
        } else if (time / 60 < 1) {
            result = App.app.getString(R.string.timeatamp_jsut_now);
        } else if (time / 60 < 60) {
            result = App.app.getString(R.string.timeatamp_minutes_ago, (time / (60) + 1) + "");
        } else if (time / (60 * 60) < 24) {
            result = App.app.getString(R.string.timeatamp_hours_ago, (time / (60 * 60) + 1) + "");
        } else if (time / (60 * 60 * 24) < 7) {
            result = App.app.getString(R.string.timeatamp_days_ago, (time / (60 * 60 * 24) + 1) + "");
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = new Date(milliseconed);
            result = format.format(d);
        }
        return result;
    }
}
