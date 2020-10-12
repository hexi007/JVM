package gc;

public class PretenureSizeThreshold {
    private static final int _1MB  = 1024 * 1024;

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

    private static void testPretenureSizeThreshold() {
        byte[] allocation = new byte[4 * _1MB];
    }
}
