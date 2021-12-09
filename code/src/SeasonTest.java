/**
 * @Author: ZJH
 * @Date: 2021/12/09/19:31
 * @Description:
 */
public class SeasonTest {

}
class Season{
    //1.声明season对象的属性
    private final String seasoName;
    private final String seasonDesc;

    //2.私有化类的构造器
    private Season(String seasoName,String seasonDesc){
        this.seasoName = seasoName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象 ：public static final
    public static final Season Spring= new Season("春天","春暖花开");
    public static final Season Summer= new Season("夏天","夏日炎炎");
    public static final Season Autumn= new Season("秋天","秋高气爽");
    public static final Season Winter= new Season("冬天","冰天雪地");

    //4. 其他诉求 ： 获取枚举类对象的属性
    public String getSeasoName() {
        return seasoName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //4. 其他诉求 ： toString
    @Override
    public String toString() {
        return "Season{" +
                "seasoName='" + seasoName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

enum Season1{
    Spring("春天","春暖花开"),
    Summer("夏天","夏日炎炎"),
    Autunm("秋天","秋高气爽"),
    Winter("冬天","冰天雪地");
    private final String seasonName;
    private final String seasonDesc;
    private Season1(String seasonName,String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

}
