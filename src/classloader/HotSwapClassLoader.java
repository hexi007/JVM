package classloader;

/**
 * @description 为了多次载入执行类而加入的加载器
 * @author 27771
 * @create 2020-10-12 20:58
 **/
public class HotSwapClassLoader extends ClassLoader{

    /**
    * @Description 虚拟机调用时，仍按原来的双亲委派规则使用loadClass方法进行类加载
    * @Param 无
    */
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
    * @Description  外部显示调用的时候才会使用到loadByte方法
    * @Param  byte[] 字节
    * @return  byte[]数组转变的Class对象
    */
    public Class loadByte (byte[] classByte){
        return defineClass(null, classByte, 0, classByte.length);
    }
}