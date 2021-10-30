public class CircleTest {
    public static void main(String[] args){
        Circle1 c1 =new Circle1();
        c1.radius = 2.1;
        double area = c1.findArea();
        System.out.println(area);
    }
}
class Circle1{
    double radius;
    public double findArea(){
        double area = Math.PI * radius * radius;
        return area;
    }
}
