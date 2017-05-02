package operate;

import com.oadb.operate.Operate;
import com.oadb.track.Track;
import org.junit.Test;

import java.util.List;

/**
 * Created by beishan on 2017/4/3.
 */
public class OperateTest {
    private Operate operate = new Operate();
    @Test
    public void test(){
        Operate operate = new Operate();
        operate.getDevices();
    }

    @Test
    public void getDevices(){
        Operate operate = new Operate();
        List<String > mobiles = operate.getDevices();
        if(mobiles != null && mobiles.size() > 0){
            System.out.println("当前在线" + mobiles.size() + "台");
            for(String mobile : mobiles){
                System.out.println(mobile);
            }
        }else {
            System.out.println("当前没有在线设备");
        }

    }

    @Test
    public void getDeviceState(){
        Operate operate = new Operate();
        operate.getDeviceState("4TTKDIAAH64T5T7L");
    }

    @Test
    public void reboot(){
        Operate operate = new Operate();
        operate.rebootDevice("4TTKDIAAH64T5T7L");
    }

    @Test
    public void install(){
        Operate operate = new Operate();
        operate.installApp("4TTKDIAAH64T5T7L",
                "E:\\android_test\\app\\chaoshengbo.apk",
                true);
    }

    @Test
    public void uninstall(){
        Operate operate = new Operate();
        operate.uninstallApp("4TTKDIAAH64T5T7L",
                "com.zuilot.chaoshengbo",
                false);
    }

    @Test
    public void runMonkey(){
        Operate operate = new Operate();
        operate.runMonkey("4TTKDIAAH64T5T7L",
                "com.zuilot.chaoshengbo",
                1000,
                "1",
                null,
                "100");
    }

    @Test
    public void trackCpu(){
        Track track = new Track();
        track.trackCpu("4TTKDIAAH64T5T7L",
                "com.zhihu.android");
    }

    @Test
    public void getDevicesMacTest(){
        String serialno = "57d6baae";
        operate.queryDevicesMacAdderss(serialno);
        System.out.println("end");
    }
}
