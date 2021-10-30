public class Circle {
    double radius;
    public static void main(String[] args){
        PassObject test = new PassObject();
        Circle c = new Circle();
        test.printAreas(c, 5);
        System.out.println((c.radius));
    }
    public double findArea(){
        return Math.PI * radius * radius;
    }
}
class PassObject{
    public void printAreas(Circle c, int time){
        for(int i = 1; i <= time; i++){
            c.radius = i;
            System.out.println(c.findArea());
        }
    }
}
