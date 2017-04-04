package operate;

import com.oadb.operate.Operate;
import org.junit.Test;

/**
 * Created by beishan on 2017/4/4.
 */
public class FlowTest {

    @Test
    public void test(){
        String serialno = "4TTKDIAAH64T5T7L";
        String apkUri = "E:\\android_test\\app\\chaoshengbo.apk";
        String packagename = "com.zuilot.chaoshengbo";
        Operate operate = new Operate();
        // 安装应用
        System.out.println("开始安装应用...");
        int ir = operate.installApp(serialno, apkUri, true);
        if(ir == 1){
            System.out.println("安装成功");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //运行monkey
            System.out.println("开始monkey测试...");
            int or = operate.runMonkey(serialno, packagename, 1000, "1", null, "100");

            if(or == 1){
                System.out.println("monkey 测试完成， 结果：完成");

                System.out.println("开始卸载应用...");
                int ur = operate.uninstallApp(serialno, packagename, false);
                if(ur == 1){
                    System.out.println("卸载完成");
                }else {
                    System.out.println("卸载失败");
                }
            }else{
                System.out.println("monkey 测试完成，结果：失败");
            }
        }else {
            System.out.println("安装失败");
        }
        // 运行monkey测试

        // 卸载应用
    }

}
