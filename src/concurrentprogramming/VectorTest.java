package concurrentprogramming;

import java.util.Vector;

/**
 * description Vector测试
 *
 * @author 27771
 * create 2020-10-16 17:11
 **/
public class VectorTest {
    private static Vector<Integer> vector = new Vector<Integer>();

    /**
     * 理论上来说程序运行会出现异常，因为如果一个线程恰好在错误的时间里删除了一个元素
     * 导致序号i已不再可用，再用i访问数组就会抛出异常，但实际运行并没有。。。
     * 如皋要确保安全的话可以在run()内添加同步块——synchronized(vector){执行内容}
     * @param args 1
     * return
     */
    public static void main(String[] args) {
        int vectorSize = 10;
        int threadSize = 20;
        while (true){
            for(int i = 0; i < vectorSize; i++){
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < vector.size(); i++){
                        vector.remove(i);
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < vector.size(); i++){
                        System.out.println(vector.get(i));
                    }
                }
            });

            removeThread.start();
            printThread.start();

            while (Thread.activeCount() > threadSize);
        }
    }
}