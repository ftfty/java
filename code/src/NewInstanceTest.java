import org.junit.Test;

import java.util.Random;

/**
 * @Author: ZJH
 * @Date: 2022/01/07/13:20
 * @Description: 通过反射创建对应的运行时类的对象
 */
public class NewInstanceTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {

        Class<Person> clazz = Person.class;
        /*
        * newInstance():调用此方法，创建对应的运行时类的对象
        * 内部调用了运行时类的空参构造器
        * 要想此方法正常的创建运行时类的对象，要求：
        * 1. 运行时类必须提供空参构造器
        * 2. 空参构造器的访问权限要够。通常设置为public
        *
        *  在Javabean 中要求提供 一个 public 的空参构造器。
        * 1. 便于通过反射，创建运行时类的对象
        * 2. 便于子类继承此运行时类时，默认调用super()时，保证父类由此构造器
        *
        **/
        Person obj = clazz.newInstance();
        System.out.println(obj);
    }

    @Test
    public void test2(){
        int num = new Random().nextInt(3); //0,1,2
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 3:
                classPath = "Person";
                break;
        }
        try {
            Object obj = getInstance(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    /*
    *创建一个指定类的对象
    * classPath ： 指定类的全类名
    *
    **/
    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
