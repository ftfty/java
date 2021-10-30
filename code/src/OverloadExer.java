public class OverloadExer {
    public void main(int i){
        System.out.println(i * i);
    }
    public void main(int i, int j){
        System.out.println(i * j);
    }
    public void main(String s){
        System.out.println(s);
    }
}
