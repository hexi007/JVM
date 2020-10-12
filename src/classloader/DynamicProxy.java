package classloader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 27771
 */
public class DynamicProxy {

    /**
     * 定义一个码农接口,它有一个实现用户需求的方法
     */
    public static interface ICoder {

        /**
         * 定义了接口
         * @param demandName 需求名字
         * @return void
         */
        public void implDemands(String demandName);
    }

    /**
     * 码农代理类，实现码农接口
     */
    public static class JavaCoder implements ICoder {

        //被代理码农名字
        private String name;

        public JavaCoder(String name) {
            this.name = name;
        }

        /**
         * 根据需求名字输出实现需求
         * @param demandName 需求名字
         * @return void
         */
        @Override
        public void implDemands(String demandName) {
            System.out.println(name + " implemented demand: " + demandName + " in Java!");
        }
    }

    /**
     * 在使用动态代理时，我们需要定义一个位于代理类与委托类之间的中介类，也叫动态代理类
     * 这个类被要求实现InvocationHandler接口：
     */
    public static class CoderDynamicProxy implements InvocationHandler{

        //被代理的实例
        private ICoder iCoder;

        public CoderDynamicProxy(ICoder iCoder) {
            this.iCoder = iCoder;
        }

        /**
        * @Description:  调用被代理的方法
        * @Param: proxy 代理； method 被代理方法； args 参数
        * @return: result结果
        */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //格式化输出时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()));

            Object result = method.invoke(iCoder, args);

            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()));
            return result;
        }
    }

    /**
    * @Description 测试动态代理
    * @Param  args 命令行参数
    * @return  void
    */
    public static void main(String[] args) {
        //要代理的真实对象
        ICoder iCoder = new JavaCoder("Li");
        //创建中介类实例
        InvocationHandler handler = new CoderDynamicProxy(iCoder);
        //获取类加载器
        ClassLoader classLoader = iCoder.getClass().getClassLoader();
        //动态产生一个代理类
        ICoder proxy = (ICoder) Proxy.newProxyInstance(classLoader,
                iCoder.getClass().getInterfaces(), handler);
        //通过代理类执行方法
        proxy.implDemands("Modify user management");
    }
}
