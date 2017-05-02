package com.oadb.util;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by beishan on 2017/4/3.
 */
public class AdbUtils {

    public static void killAdbServer(){
        AdbUtils.executeCmd("adb kill-server");
    }

    public static void restartAdbServer(){
        AdbUtils.executeCmd("adb start-server");
    }
    /**
     * 执行adb 命令
     * @param cmd
     * @return
     */
    public static String executeCmd(String cmd){
        //BufferedReader inputStream = null;
        //BufferedWriter outputStream = null;
        //BufferedReader errorStream = null;
        String result = null;
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            final BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            final BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            final BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = inputStream.readLine()) != null){
                sb.append(line + "\r\n");
                System.out.println(line);
            }
            result = sb.toString();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static String readLine(BufferedReader bufferedReader){
        String result = null;
        Callable<String> readLine = new ReadLine(bufferedReader);
        FutureTask<String> ft = new FutureTask<String>(readLine);
        Thread thread = new Thread(ft);
        thread.start();
        System.out.println("启动线程");
        try {
            result = ft.get();
            System.out.println("result:" + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static String readOutPut(final BufferedReader br){

        System.out.println("进入：");

        final StringBuffer result = new StringBuffer();
        new Thread(new Runnable() {
            public void run() {
                System.out.println("开始读取数据");

                String line = null;
                try{
                    while ((line = br.readLine()) != null){
                        System.out.println(line);
                        result.append(line + "\r\n");
                    }
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

        return result.toString();
    }
}
