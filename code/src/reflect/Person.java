package reflect;

/**
 * @Author: ZJH
 * @Date: 2022/01/07/15:00
 * @Description:
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>, MyInterface{


    private String name;
    int age;
    public int id;

    public Person(){};

    @MyAnnotation(value = "abc")
    private Person(String name){
        this.name = name;
    }

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍 " + nation);
        return nation;
    }

    public String display(String interests){
        return interests;
    }

    @Override
    public int compareTo(String s) {
        return 0;
    }

    @Override
    public void info() {

    }
}
