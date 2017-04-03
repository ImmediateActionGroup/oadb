package com.oadb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 * Created by beishan on 2017/4/4.
 */
public class ReadLine implements Callable<String> {
    private BufferedReader input = null;
    public ReadLine(){

    }
    public ReadLine(BufferedReader input) {
        this.input = input;
    }

    public String call(){
        if(input != null){
            String line = null;
            try {
                StringBuffer result = new StringBuffer();
                while ((line = input.readLine()) != null){
                    System.out.println("line----:" + line);
                    result.append(line + "\r\n");
                }
                input.close();
                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
