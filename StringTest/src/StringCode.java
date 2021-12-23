/**
 * @Author: ZJH
 * @Date: 2021/12/23/19:21
 * @Description:
 */
public class StringCode {
    public static void main(String[] args) {
        String s111 = new String("a");
        String s222 = new String("a");
        System.out.println(s111 == s222);
        System.out.println(s111.equals(s222));
        /** 第1问 **/
        String s1 = "a";
        String s2 = "a";
        System.out.println("第1问：" + (s1 == s2));

        /** 第2问 **/
        String s3 = new String("b");
        String s4 = new String("b");
        System.out.println("第2问：" + (s3 == s4));

        /** 第3问 **/
        String s5 = new String("c");
        String s6 = "c";
        System.out.println("第3问：" + (s5 == s6));

        /** 第4问 **/
        String s7 = "d" + "e";
        String s8 = "de";
        System.out.println("第4问：" + (s7 == s8));

        /** 第5问 **/
        String s9 = new String("g") + new String("h");
        String s10 = "gh";
        System.out.println("第5问：" + (s9 == s10));

        /** 第6问 **/
        String s11 = new String("i") + new String("j");
        s11.intern();
        String s12 = "ij";
        System.out.println("第6问：" + (s11 == s12));

        /** 第7问 **/
        String s13 = "kl";
        String s14 = new String("k") + new String("l");
        s14.intern();
        System.out.println("第7问：" + (s13 == s14));

        /** 第8问 **/
        String s15 = new String("m");
        s15.intern();
        String s16 = "m";
        System.out.println("第8问：" + (s15 == s16));


    }
}
