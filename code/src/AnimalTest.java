public class AnimalTest {
    public static void main(String[] args){
        Animal a = new Animal();
        a.setLegs(4);
        System.out.println(a.toString());
    }
}
class Animal {
    private String name;
    private int age;
    private int legs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public int getLegs() {
        return legs;
    }

    public void eat() {
        System.out.println("eat");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", legs=" + legs +
                '}';
    }
}
