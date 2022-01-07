package reflect2;

import org.junit.Test;
import reflect.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Author: ZJH
 * @Date: 2022/01/07/15:14
 * @Description: 获取运行时类的属性结构
 */
public class FieldTest {

    @Test
    public void test1(){

        Class clazz = Person.class;

        //获取属性结构
        //getFields(): 获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field f : fields){
            System.out.println(f);
        }

        //getDeclaredFields() : 获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f:
             fields1) {
            System.out.println(f);
        }

    }

    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){

        Class clazz = Person.class;
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f:
                fields1) {
            //1.权限修饰符
            int modifiers = f.getModifiers();
            System.out.println(Modifier.toString(modifiers));

            //2. 数据类型
            Class type = f.getType();
            System.out.println(type.getName());

            //3. 变量名
            f.getName();

            System.out.println(f);
        }

    }
}
