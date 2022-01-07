package reflect2;

import org.junit.Test;
import reflect.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Author: ZJH
 * @Date: 2022/01/07/15:32
 * @Description:
 */
public class MethodTest {

    @Test
    public void test1(){

        Class clazz = Person.class;

        //getMethods() : 获取当前运行时类及其搜游父类中声明为public的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }

        System.out.println();

        // getDeclaredMethods() : 获取当前运行时类中声明的所有方法，不包含父类中的
        Method[] declareMethods = clazz.getDeclaredMethods();
        for(Method m : declareMethods){
            System.out.println(m);
        }
    }

    /**
     * @XX
     * 权限修饰符 返回值类型 方法名（参数类型。。。） throws XxxException{}
    **/
    @Test
    public void test2(){

        Class clazz = Person.class;
        Method[] declareMethods = clazz.getDeclaredMethods();
        for(Method m : declareMethods){

            //1. 获取方法声明的注解
            Annotation[] annos = m.getAnnotations();
            for (Annotation a : annos){
                System.out.println(a);
            }

            //2. 权限修饰符
            System.out.println(Modifier.toString(m.getModifiers()));
            System.out.println();

            //3. 返回值类型
            System.out.println(m.getReturnType().getName());
            System.out.println();

            //4. 方法名
            System.out.println(m.getName());

            //5. 形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes == null && parameterTypes.length == 0)){
                for(Class p : parameterTypes){
                    System.out.println(p.getName() + "args_");
                }
            }

            //6. 抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();


            System.out.println(m);
        }
    }

}
