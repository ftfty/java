public class USBTest {
    public static void main(String[] args){
        Computer com = new Computer();
        //1.创建了接口的非匿名实现类的非匿名对象
        Flash flash = new Flash();
        com.transferData(flash);
        //2. 创建了接口的非匿名实现类的匿名对象
        com.transferData(new Printer());
        //3.创建了接口的匿名实现类的非匿名对象
        USB phone = new USB(){
            public void start(){
                System.out.println("开启工作");
            }
            public void stop(){
                System.out.println("结束工作");
            }
        };
        //4.创建了接口的匿名实现类的匿名对象
        com.transferData(new USB(){
            public void start(){
                System.out.println("开启工作");
            }
            public void stop(){
                System.out.println("结束工作");
            }
        });
    }
}
class Computer{
    public void transferData(USB usb){
        usb.start();
        System.out.println("具体传输数据的细节");
        usb.stop();
    }
}
interface USB{
    void start();
    void stop();
}
class Flash implements USB{
    public void start(){
        System.out.println("U盘开启工作");
    }
    public void stop(){
        System.out.println("U盘结束工作");
    }
}
class Printer implements USB{
    public void start(){
        System.out.println("打印机开启工作");
    }
    public void stop(){
        System.out.println("打印机结束工作");
    }
}
