package extendtest;

import extendtest.Person;

public class Student extends Person {
    String major;
    public Student(){

    }
    public void eat(){
        System.out.println("吃饭2");
    }
    public Student(String major){
        this.major = major;
    }
    public void study(){
        System.out.println("学习，专业是"+major);
    }
}
