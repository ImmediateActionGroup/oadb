package com.ete.executor;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.*;

/**
 * 本地命令执行帮助类
 * Created by xueshan.wei on 5/11/2017.
 */
public class CmdHelper {

    public static String exeCmd(String cmd){
        String line = "java -version";
        CommandLine commandLine = CommandLine.parse(line);
        DefaultExecutor executor = new DefaultExecutor();
        //executor.setExitValue(1);
        try {
            int exitValue = executor.execute(commandLine);
            if(exitValue == 1){
                System.out.println("执行成功！");
            }else {
                System.out.println("执行失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行命令并返回输出的结果
     * @param cmd 执行的命令
     * @return 输出结果
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
}
