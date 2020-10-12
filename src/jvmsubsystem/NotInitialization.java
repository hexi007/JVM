package jvmsubsystem;

class SuperClass {
    public static int value = 123;

    static {
        System.out.println("SuperClass init");
    }
}

class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init");
    }
}

class ConstClass {
    public static final String HelloWorld = "HelloWorld";

    static {
        System.out.println("ConstClass init");
    }

}

public class NotInitialization {
    public static void main(String[] args) {
        //通过子类引用父类的静态字段，不会导致子类初始化
        //System.out.println(SubClass.value);
        //System.out.println(SuperClass.value);

        //通过数组定义引用类，不会触发此类的初始化
        //SuperClass[] sca = new SuperClass[10];

        //常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类
        //因此不会触发定义常量的类的初始化
        //System.out.println(ConstClass.HelloWorld);

        SubClass subClass = new SubClass();
        SuperClass superClass = (SuperClass) subClass;
    }
}
