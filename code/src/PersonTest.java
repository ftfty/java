public class PersonTest{
    public static void main(String[] args){
        Person person = new Person();
        person.name = "tom";
        System.out.println(person.name);
        person.eat();
    }
}
class Person{
    String name;
    int age = 1;
    boolean isMale;
    public void eat(){
        System.out.println("eat");
    }
}
