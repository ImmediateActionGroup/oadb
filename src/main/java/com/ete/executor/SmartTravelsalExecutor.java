package com.ete.executor;

/**
 * 智能遍历执行类
 * Created by xueshan.wei on 5/11/2017.
 */
public class SmartTravelsalExecutor {
    private static final String APPCRAWLER_ADDRESS = "E:\\work\\smarttraversal\\appcrawler-2.0.1.jar";

    public void start(){
        String cmd = "java -jar E:\\work\\smarttraversal\\appcrawler-2.0.1.jar -a E:\\work\\smarttraversal\\BaiduNuomi.apk";

        String result = CmdHelper.executeCmd(cmd);
    }
    public void start(String appUrl){
        start(appUrl, 0, "", "", "", "");
    }
    public void start(String appUrl, int maxtime, String confPath, String platform,
                      String outputPath, String appiumUrl){
        String cmd = "java -jar " + APPCRAWLER_ADDRESS + " -a " + appUrl;
        System.out.println(cmd);
        String result = CmdHelper.executeCmd(cmd);
    }
}
