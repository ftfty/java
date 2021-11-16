public class CircleTest {
    public static void main(String[] args){
        Circle1 c1 =new Circle1();
        double area = c1.findArea();
        System.out.println(area);
    }
}
class Circle1{
    private double radius;
    private int id;

    public double getRadius() {
        return radius;
    }

    public Circle1(double radius){
        this();
      this.radius =radius;
       // id = init++;
        //total++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Circle1(){
        id = init++;
    }
    private static int total;
    private static int init = 1001;
    public double findArea(){
        double area = Math.PI * radius * radius;
        return area;
    }
}
