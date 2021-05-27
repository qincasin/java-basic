package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by qincasin on 2021/5/26.
 */
public class TestClassLoad {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clz = Class.forName("reflect.A");
        Object o = clz.newInstance();
        Method m = clz.getDeclaredMethod("hello", null);
        m.invoke(o);
    }
}
