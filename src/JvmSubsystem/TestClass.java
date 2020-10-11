package JvmSubsystem;

public class TestClass {
    private int m;

    public int inc() {
        return m + 1;
    }

    public static void main(String[] args) {
        int i = Integer.MAX_VALUE + 1;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(i);
    }
}
