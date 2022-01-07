import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @Author: ZJH
 * @Date: 2022/01/07/11:28
 * @Description:
 */
public class reflect {
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Person.class;
        //1. 通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
    }

    @Test
    public void test3() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性，“.class”
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        //方式二： 通过运行时类的对象
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三： 调用Class的静态方法： forName(String classPath)
        Class clazz3 = Class.forName("Person");
        Class clazz31 = Class.forName("java.lang.String");
        System.out.println(clazz3 + "" + "" + clazz31);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);

        //方式四：使用类的加载器：ClassLoader
        ClassLoader classLoader = reflect.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);
    }

    @Test
    public void test1() {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = reflect.class.getClassLoader();
        System.out.println(classLoader);

        //调用系统类加载器的getParent() : 获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //调用扩展类加载器的getParent() : 无法获取引导类加载器
        //引导类加载器主要负责加载Java核心类库，无法加载自定义类
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

    }

    @Test
    public void test4() throws IOException {
        Properties pros = new Properties();

//        此时的文件默认在当前的module下
//        读取配置文件的方式一：

//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
//        pros.load(fis);

        //读取配置文件的方式二：使用classLoader
        //注意：配置文件默认识别为：当前module的src下
        ClassLoader classLoader = reflect.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password" + password);

    }
}
