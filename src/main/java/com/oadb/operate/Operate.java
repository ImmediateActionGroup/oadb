package com.oadb.operate;

import com.oadb.util.AdbUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beishan on 2017/4/3.
 */
public class Operate {
    private final String DEVICES = "adb devices";


    private final String DEVICE_ONLINE = "device";
    private final String DEVICE_OFFLINE = "offline";
    private final String DEVICE_UNKNOWN = "unknown";
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

    public List<String> getDevices(){
        String result = AdbUtils.executeCmd(DEVICES);
        System.out.println(result);
        List<String> devices = null;
        if(result != null){
            devices = new ArrayList<String>();
            String [] lines = result.split("\r\n");
            String device = null;
            for(String line : lines){
                System.out.println(line);
                if(line.endsWith("device")){
                    device = line.substring(0, line.length() - 6);
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

    public void rebootDevice(String serialno){
        if(serialno != null){
            String cmd = "adb -s " + serialno + " reboot";
            AdbUtils.executeCmd(cmd);
        }
    }

    public int installApp(String serialno, String apkUri, boolean isCover){
        String cover = isCover ? "-r " : "";
        File file = new File(apkUri);
        if(file.exists()){
            String cmd = "adb -s " + serialno + " install " + cover + apkUri;
            String result = AdbUtils.executeCmd(cmd);
            System.out.println(result);
        }

        return 0;
    }
}
