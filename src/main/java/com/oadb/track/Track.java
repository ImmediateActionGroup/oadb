package com.oadb.track;

import com.oadb.util.AdbUtils;

/**
 * Created by beishan on 2017/4/4.
 */
public class Track {

    public void trackCpu(String serialno, String packagename){
        String cmd = "adb -s " + serialno + " shell top | grep \""
                + packagename + "\"";
        String result = AdbUtils.executeCmd(cmd);
        System.out.println(result);
    }
}
