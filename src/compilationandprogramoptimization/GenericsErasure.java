package compilationandprogramoptimization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description 泛型擦除测试
 * @author 27771
 * create 2020-10-13 17:04
 **/
public class GenericsErasure {

    /**
     * 泛型擦除后24，25行反编译如下
     * System.out.println((String)map.get("hello"));
     * System.out.println((String)map.get("how are u?"));
     * 元素访问时插入了从Object到String的强制类型转换
     * @return  空
     */
    public static void basicGenericsErasureTest(){
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("hello", "hi");
        map.put("how are u?", "I'm fine");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are u?"));

    }

    /**
     * 被注释程序无法编译运行，因为参数List<String> list和List<Integer> list编译之后被擦除了
     * 变成了同一种裸类型List，因而两个方法特征签名变得一模一样，所以无法重载
    public static void method(List<String> list){
        System.out.println("invoke method(List<String> list)");
    }

    public static void method(List<Integer> list){
        System.out.println("invoke method(List<String> Integer)");
    }
     */

    /**
     * 泛型擦除之后，即使返回值不同，依然不能重载
    public static String method(List<Integer> list){
        System.out.println("invoke method(List<String> list)");
        return "";
    }

    public static int method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }
     */

    public static void main(String[] args) {
        basicGenericsErasureTest();
    }
}