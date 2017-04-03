package util;

import com.oadb.util.AdbUtils;
import com.oadb.util.ThreadRun;
import org.junit.Test;

/**
 * Created by beishan on 2017/4/3.
 */
public class AdbUtilTest {

    @Test
    public void executeCmd() throws Exception{
        String cmd = "adb devicess";
        String result = AdbUtils.executeCmd(cmd);
        System.out.println("测试得到的结果：");
        System.out.println(result);
    }

    @Test
    public void test(){
        ThreadRun.test1();
    }
}
