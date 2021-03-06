package gc;

import java.util.ArrayList;
import java.util.List;

public class FillHeap {
    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        //while (true);
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.gc();
        while (true);
    }
}
