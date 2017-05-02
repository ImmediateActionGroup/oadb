package com.oadb.operate;

import com.oadb.util.AdbUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beishan on 2017/4/3.
 */
public class Operate {
    private final String CMD_DEVICES = "adb devices";

    //设备状态
    private final String DEVICE_ONLINE = "device"; //正常在线
    private final String DEVICE_OFFLINE = "offline"; // 出现异常或者其他
    private final String DEVICE_UNKNOWN = "unknown"; // 不在线

    private final String MONKEY_LOG_DIR = "E:\\\\android_test\\\\monkey\\\\logs\\\\";
    private final String MONKEY_LOG_DIR_2 = "/sdcard/monkey/logs/";

    public String operate(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            OutputStream outputStream = process.getOutputStream();
            final InputStream inputStream = process.getInputStream();
            new Thread(new Runnable(){
                byte[] cache = new byte[1024];
                public void run() {
                    System.out.println("listener started");
                    try {
                        while(inputStream.read(cache)!=-1){
                            System.out.println(new String(cache));
                        }
                    } catch (IOException e) {
                        //e.printStackTrace();
                    }
                }
            }).start();
            outputStream.write(new byte[]{'d','i','r','\n'});
            //inputStream.
            int i = 0;
            try {
                i = process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i=" + i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询在线设备
     * @return 在线的设备 | null 表示没有设备
     */
    public List<String> getDevices(){
        String result = AdbUtils.executeCmd(CMD_DEVICES);
        List<String> devices = null;
        if(result != null){
            devices = new ArrayList<String>();
            String [] lines = result.split("\r\n");
            String device = null;
            for(String line : lines){
                if(line.endsWith("device")){
                    device = StringUtils.trim(line.substring(0, line.length() - 6));
                    devices.add(device);
                }
            }
            return devices;
        }
        return null;
    }

    /**
     * check device state
     *
     * @param serialno
     * @return -1 unknown | 0 offline | 1 device
     */
    public int getDeviceState(String serialno){

        if(serialno != null){
            String cmd = "adb -s " + serialno + " get-state";
            String result = AdbUtils.executeCmd(cmd);
            if(result != null){
                if(DEVICE_ONLINE.equals(result)){
                    return 1;
                }
                if(DEVICE_OFFLINE.equals(result)){
                    return 0;
                }
                if(DEVICE_UNKNOWN.equals(result)){
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * 重启设备
     * @param serialno
     */
    public void rebootDevice(String serialno){
        if(serialno != null){
            String cmd = "adb -s " + serialno + " reboot";
            AdbUtils.executeCmd(cmd);
        }
    }

    /**
     * install app
     * @param serialno 设备序列号
     * @param apkUri apk路径
     * @param isCover 是否覆盖安装
     * @return 1 安装成功 | 0 apk路径错误 | -1 安装失败
     */
    public int installApp(String serialno, String apkUri, boolean isCover){
        String cover = isCover ? "-r " : "";
        File file = new File(apkUri);
        if(file.exists()){
            String cmd = "adb -s " + serialno + " install " + cover + apkUri;
            String result = AdbUtils.executeCmd(cmd);
            if(result.trim().endsWith("Success")){
                return 1;
            }else{
                return -1;
            }
        }
        return 0;
    }

    /**
     * uninstall app
     *
     * @param serialno 设备序列号
     * @param packagename 包名
     * @param isKeepCache 是否保留数据和缓存目录
     * @return 1 success | -1 failure
     */
    public int uninstallApp(String serialno, String packagename, boolean isKeepCache){
        String keep = isKeepCache ? "-k " : "";
        String cmd = "adb -s " + serialno + " uninstall " + keep + packagename;
        String result = AdbUtils.executeCmd(cmd);
        if(result != null && result.trim().startsWith("Success")){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * run monkey test
     * @param serialno
     * @param packagename
     * @param times count
     * @param v log level [null, 1, 2, 3]
     * @param s seed [null | long ]
     * @param throttle
     * @return 1 success | -1 error
     */
    public int runMonkey(String serialno, String packagename, Integer times,
                         String v, String s, String throttle){
        String outFileName = packagename + "_monkey.log";
        String vLevel = null;
        if(v == null){
            vLevel = "";
        }else if("1".equals(v)){
            vLevel = "-v ";
        }else if("2".equals(v)){
            vLevel = "-v -v ";
        }else if("3".equals(v)){
            vLevel = "-v -v -v ";
        }
        String seed = "";
        if(s != null){
            seed = "-s " + s + " ";
        }
        String th = "";
        if(throttle != null){
            th = "--throttle " + throttle + " ";
        }
        String out = ">" + MONKEY_LOG_DIR + outFileName;
        String cmd = "adb -s " + serialno + " shell monkey -p "
                + packagename + " " + th + seed + vLevel
                + times.toString() + " ";
        System.out.println(cmd);
        String result = AdbUtils.executeCmd(cmd);
        if(result != null && result.trim().endsWith("Monkey finished")){
            return 1;
        }
        return -1;
    }

    public int runMonkey(String serialno, String packagename, Integer times){
        return runMonkey(serialno, packagename, times,
                "1", null, "200");
    }

    //截图


    //获取机器mac地址

    /**
     * 获取设备的mac地址
     *
     * @param serialno
     * @return 返回该设备的mac地址 | 空字符（当传入的serialno的设备不在线或者错误时）
     */
    public String queryDevicesMacAdderss(String serialno){
        String cmd = "adb -s " + serialno + " shell cat /sys/class/net/wlan0/address";
        String result = AdbUtils.executeCmd(cmd);
        result = StringUtils.deleteWhitespace(result);
        return result;
    }

    /**
     * kill 某个进程
     * @param serialno
     * @param pid
     * @return
     */
    public String killProcesses(String serialno, String pid){
        String cmd = "adb -s " + serialno + " shell kill " + pid;
        String result = AdbUtils.executeCmd(cmd);
        System.out.println(result);
        // TODO: 5/2/2017 这里还需要处理，暂时用不到这个函数，先不写 
        return null;
    }
}
