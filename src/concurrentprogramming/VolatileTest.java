package concurrentprogramming;

/**
 * description volatile变量自增运算测试
 *
 * @author 27771
 * create 2020-10-16 15:02
 **/
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase(){
        race++;
    }

    private static final int THREADS_COUNT = 20;

    /**
     * Description  volatile不能保证并发正确
     * @param args 1
     * return  空
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        final int rounds = 10000;
        for(int i = 0; i < THREADS_COUNT; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < rounds; i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(race);

    }
}