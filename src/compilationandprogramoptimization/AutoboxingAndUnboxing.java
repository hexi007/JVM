package compilationandprogramoptimization;

/**
 * description 自动拆装箱
 * @author 27771
 * create 2020-10-13 19:23
 **/
public class AutoboxingAndUnboxing {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Long g = 3L;


        System.out.println(c == d);

        //括号内被反编译为c == a + b;
        System.out.println(c == (a + b));

        //比较两边都是int
        System.out.println(c.equals(a + b));

        //括号内被反编译为g == (long)(a + b)，发生了自动装箱
        System.out.println(g == (a + b));

        //Long类型和int类型不等
        System.out.println(g.equals(a + b));

        /*
        因为没有自动装箱，所以直接比较会编译错误
        System.out.println(d == g);
         */

        //结果为   true    true    true    true    false
        //最终结论，“==”运算在不遇到算术运算时不会自动装箱
    }
}