package JvmSubsystem;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * jdk7 update 9 之后不可得到祖类方法
 * @author 27771
 */
public class MethodHandleSon {

    static class Grandfather {
        void talking () throws Throwable {
            System.out.println("I'm grandfather.");
        }
    }

    static class Father extends Grandfather {
        @Override
        void talking () throws Throwable {
            System.out.println("I'm father.");
        }
    }

    static class Son extends Father {
        @Override
        void talking() {
            try {
                //void.class为methodType第一个参数，即返回值
                MethodType mt = MethodType.methodType(void.class);
                //在指定类中查找给定的方法名称，方法类型，并且符合调用权限的方法句柄
                MethodHandle mh = lookup().findSpecial(Grandfather.class,"talking",mt,getClass());
                mh.invoke(this);
            } catch (Throwable e) {

            }
        }
    }

    static class NewSon extends Father {
        @Override
        void talking() throws Throwable {
            MethodType mt = MethodType.methodType(void.class);
            Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImpl.setAccessible(true);
            MethodHandle mh = ((MethodHandles.Lookup)lookupImpl.get(null) ).findSpecial(
                    Grandfather.class,"talking",mt,getClass());
            mh.invoke(this);
        }
    }

    public static void main(String[] args) throws Throwable {

        (new Son()).talking();
        (new NewSon()).talking();
    }
}
