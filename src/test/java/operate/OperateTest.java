package operate;

import com.oadb.operate.Operate;
import org.junit.Test;

/**
 * Created by beishan on 2017/4/3.
 */
public class OperateTest {

    @Test
    public void test(){
        Operate operate = new Operate();
        operate.getDevices();
    }

    @Test
    public void getDevices(){
        Operate operate = new Operate();
        operate.getDevices();

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
                false);
    }
}
