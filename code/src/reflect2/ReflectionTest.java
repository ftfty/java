package reflect2;

import org.junit.Test;
import reflect.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: ZJH
 * @Date: 2022/01/10/17:22
 * @Description:
 */
//调用运行时类中的指定结构：属性、方法、构造器
public class ReflectionTest {

    @Test
    public  void testField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //获取指定的属性 ： 要求运行时类的属性声明为public，不常用
        Field id = clazz.getField("id");

        //设置当前属性的值
        /*
        set():参数1 ：指明设置哪个对象的属性
              参数2 ：将此属性设置为多少
        **/
        id.set(p, 1001);

        /*
        获取当前属性的值
        get() : 参数1 获取哪个对象的当前属性值
        **/
        id.get(p);
    }

    @Test
    public void testField1() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //getDeclaredField(String name): 获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //保证当前属性是可访问的
        name.setAccessible(true);
        name.set(p, name);
        System.out.println(name.get(p));
    }

    @Test
    public void testMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person)clazz.newInstance();

        //获取指定的某个方法
        //getDeclaredMethods() : 参数1 ：指明获取方法的名称 参数2 ：指明获取的方法的形参列表
        Method shows = clazz.getDeclaredMethod("show", String.class);

        shows.setAccessible(true);
        //invoke(): 参数1 ：方法的调用者 参数2 ：给方法形参赋值的实参
        //invoke()方法的返回值即为对应类中调用的方法的返回值
        //如郭调用的运行时类的方法没有返回值，则invoke()返回null
        Object returnValue = shows.invoke(p, "CHN");
        System.out.println(returnValue);

        System.out.println("*****如何调用静态方法****");
        //同上
        //showDesc.invoke(Person.clss);
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Person.class;

        /**
         * 1.获取指定的构造器
         *  getDeclaredConstructor(): 参数 ：指明构造器的参数列表
        **/

        Constructor declaredConstructors = clazz.getDeclaredConstructor(String.class);

        //2.保证此构造器是可访问的
        declaredConstructors.setAccessible(true);

        //3.调用此构造器创建运行时类的对象
        Person o = (Person)declaredConstructors.newInstance("Tom");
        System.out.println(o);
    }
}
