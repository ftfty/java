package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: ZJH
 * @Date: 2022/01/13/13:42
 * @Description:
 */

interface Human{

    String getBelief();

    void eat(String food);

}

//被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "超人的信仰";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

class HumanUtil{

    public void method1(){
        System.out.println("通用方法1");
    }

    public void method2(){
        System.out.println("通用方法2");
    }

}

/*
要想实现动态代理，需要解决的问题？
问题一:如何根据加载到内存中的被代理类，动态创建一个代理类及其对象
问题二:当通过代理类的对象调用方法时，如何动态的去调用被代理类中的同名方法
*/

class ProxyFactory{
    //调用此方法返回一个代理类的对象，解决问题1
    public static Object getProxyInstance(Object obj){ //obj ：被代理类的对象

        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);

    }

}

class MyInvocationHandler implements InvocationHandler{

    //需要使用被代理类的对象进行赋值
    private Object obj;

    public void bind(Object obj){
        this.obj = obj;
    }

    //当通过代理类的对象，调用方法A时，就会自动的调用如下的方法 : invoke()
    //将被代理类要执行的方法A的功能就声明在invoke()中
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        HumanUtil util = new HumanUtil();
        util.method1();
        //method : 即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj: 被代理类的对象
        Object returnValue = method.invoke(obj, objects);
        //代理对象调用方法的返回值
        util.method2();
        return returnValue;

    }
}

public class ProxyTest {

    public static void main(String[] args) {

        SuperMan superMan = new SuperMan();
        //proxyInstance : 代理类的对象
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中的同名方法
        proxyInstance.getBelief();
        proxyInstance.eat("煎蛋");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyInstance1.produceCloth();

    }

}
