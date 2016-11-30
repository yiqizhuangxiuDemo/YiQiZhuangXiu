package com.bwf.yiqizhuangxiu.utils.indicator;

import com.bwf.yiqizhuangxiu.utils.LogUtils;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/11/29.
 */

public class TimestampUtils {
    public static String millisecondToTimestamp(long milliseconed) {
        String result = "";
        long time = Calendar.getInstance().getTimeInMillis() - milliseconed;
        LogUtils.e("TimestampUtils", (time / (1000 * 60)) + "");
        if (time / (1000l * 60l) < 60) {
            result = (time / 1000l * 60l + 1) + "分钟前";
        } else if (time / (1000l * 60l * 60l) < 24) {
            result = (time / (1000l * 60l * 60l) + 1) + "小时前";
        } else {
            result = (time / (1000l * 60l * 60l * 24l) + 1) + "天前";
        }
        return result;
    }
}
