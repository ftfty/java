public class SingletonTest {
}
//饿汉式
class Bank{
    //1.私有化类的构造器
    private Bank(){

    }
    //内部创建类的对象
    //要求此对象必须声明为静态的
    private static Bank instance = new Bank();
    //提供公共的静态方法，返回类的对象
    public static Bank getInstance(){
        return instance;
    }
}
//懒汉式
class Orders{
    //私有化类的构造器
    private Orders(){

    }
    //先声明当前类对象，没有初始化
    //此对象也必须为static
    private static Orders instance = null;

    //声明public、static的返回当前对象方法
    public  static Orders getInstance(){
        if(instance == null){
            instance = new Orders();
        }
        return instance;
    }
}
