public class RecursionTest {
    public static void main(String[] args) {
        /**
        int sum = 0;
        for(int i = 1; i <= 100; i++){
            sum += i;
        }
         **/
        RecursionTest test = new RecursionTest();
        int sum = test.getSum(100);
        System.out.println(sum);
    }
    public int getSum(int n){
        if(n == 1){
            return 1;
        }else{
            return n + getSum(n - 1);
        }
    }
}
