package HashMapTest.HashMapTest;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    static HashMap<Long, String> test = new HashMap<>();
    public static void main( String[] args )
    {
        
        test(1L);
        test(2L);
        test(3L);
        test(4L);
        test(22L);
        test(2L);
        test(6L);
        System.out.println( "Hello World!" + test);
    }

    public static void test(Long l) {
        test.computeIfAbsent(l, t -> {
            test.put(l, t.toString()+"+");
            return l.toString() + "*";
        });
    }
}
