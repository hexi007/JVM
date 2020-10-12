package gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JConsoleThreadTest {
    //线程死循环
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true){
                    //第7行
                }
            }
        },"testBusyThread");
        thread.start();
    }

    //线程锁等待演示
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }
}