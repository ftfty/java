import java.util.Arrays;

/**
 * @Author: ZJH
 * @Date: 2021/12/09/17:09
 * @Description:
 */
public class CompareTest {
    public void test1(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        Arrays.sort(arr);
        System.out.println(arr.toString());
    }

}
class Goods implements Comparable{
    private String name;
    private  double price;
    public Goods(){

    }
    public Goods(String name, double price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods = (Goods) o;
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else return 0;
//            return Double.compare(this.price,goods.price);
        }
        return 0;
//        throws new RuntimeException("传入类型不一致");
    }
    public int compare(Object o1,Object o2){
        if(o1 instanceof String && o2 instanceof String){
            String s1 = (String) o1;
            String s2 = (String) o2;
            return -s1.compareTo(s2);
        }
        return 0;

    }
}
