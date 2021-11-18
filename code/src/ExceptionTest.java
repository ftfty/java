import org.junit.Test;

import java.util.Date;
import java.util.Scanner;

public class ExceptionTest {
    //NullPointerException
    @Test
    public void test1(){
        int arr[] = null;
        System.out.println(arr[3]);
    }
    //java.lang.ClassCastException
    @Test
    public void test2(){
        Object obj = new Date();
        String str = (String) obj;
    }
    //ArrayIndexOutOfBoundsException
    @Test
    public void test3(){
        int arr[] = new int[3];
        System.out.println(arr[4]);
    }
    //NumberFormatException
    @Test
    public void test4(){
        String str = "abc";
        int num = Integer.parseInt(str);
    }
    //InputMismatchException
    @Test
    public void test5(){
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        System.out.println(score);
    }
    //ArithmeticException
    @Test
    public void test6(){
        int a = 10;
        int b = 0;
        System.out.println(a / b);
    }
}
