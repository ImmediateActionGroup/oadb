package executor;

import com.ete.executor.SmartTravelsalExecutor;
import org.junit.Test;

/**
 * Created by xueshan.wei on 5/11/2017.
 */
public class SmartTravelsalExecutorTest {
    @Test
    public void testStart() throws Exception{
        String appUrl = "E:\\work\\smarttraversal\\BaiduNuomi.a2pk";
        SmartTravelsalExecutor ste = new SmartTravelsalExecutor();
        ste.start(appUrl);
    }
}
