package compilationandprogramoptimization;

import java.util.HashMap;
import java.util.Map;

/**
 * description 泛型擦除测试
 * @author 27771
 * create 2020-10-13 17:04
 **/
public class GenericsErasure {

    /**
     * 泛型擦除后23，24行反编译如下
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
    public static void main(String[] args) {
        basicGenericsErasureTest();
    }
}