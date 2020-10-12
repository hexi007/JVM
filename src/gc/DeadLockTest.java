package gc;

public class DeadLockTest {
    //线程死锁等待演示
    static class SynAddRunnable implements Runnable {
        int a,b;
        public SynAddRunnable(int a,int b){
            this.a = a;
            this.b = b;
        }

        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a + b);
                }
            }
        }
    }

    //Integer.valueOf()方法基于减少对象创建次数和节省内存考虑，-128到127之间的数都会被缓存。
    //也就是调用200次valueOf方法只有两个不同的对象。
    //假如某个线程的两个synchronized块发生线程切换的话，就会出现线程A和线程B互相等待的情况，造成死锁。
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(new SynAddRunnable(1,2)).start();
            new Thread(new SynAddRunnable(2,1)).start();
        }
    }
}
