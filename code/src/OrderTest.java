public class OrderTest {
    public static void main(String[] args){
        Order oder = new Order();
        oder.orderDefault = 1;
        oder.orderPublic = 2;
        //出了order类，私有的结构就不可以调用了
        //出了order类所属的包以后，私有，缺省结构就不可以调用了
    }
}
