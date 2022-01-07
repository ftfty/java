package reflect;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2022/01/07/15:00
 * @Description:
 */
public class Creature<T> implements Serializable {
    private  char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物进食");
    }
}
