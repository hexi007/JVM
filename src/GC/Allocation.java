package GC;

public class Allocation {

    private static final int _1MB  = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //System.gc();
        allocation4 = new byte[4 * _1MB];

//        //allocation5 = new byte[3 * _1MB];
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testAllocation();
    }

}