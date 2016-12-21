package util;

import java.util.Collection;

/**
 * Created by Vergir on 21/12/2016.
 */
public class Util {
    public static <T> T randomFrom(Collection<T> c) {
        Object[] array = c.toArray();
        return (T)array[Double.valueOf(Math.random()*c.size()).intValue()];
    }
}
