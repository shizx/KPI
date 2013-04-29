package com.zuyue.system;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-1-16
 * Time: 14:42:53
 * To change this template use File | Settings | File Templates.
 */
public final class Environment {
    private static Date applicationStartTime;

    /** 设置系统的启动时间 */
    public static void setApplicationStartTime(final Date startTime) {
        applicationStartTime = startTime;
    }

    /** 获得系统的启动时间 */
    public static Date getApplicationStartTime() {
        return applicationStartTime;
    }
}
