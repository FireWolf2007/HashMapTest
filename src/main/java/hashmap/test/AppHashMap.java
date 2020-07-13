package hashmap.test;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class AppHashMap {
    private static final Logger log = LoggerFactory.getLogger(AppHashMap.class);
    private static HashMap<Long, String> test = new HashMap<>();

    public static void main(String[] args) {
        test(1L);
        test(2L);
        test(3L);
        test(4L);
        test(22L);
        test(2L);
        test(6L);
        /**
         * On JDK 8 expected: HashMap: {1=1*, 2=2*, 3=3*, 4=4*, 6=6*, 22=22*, 6=6+}
         * On JDK 9+ ConcurrentModificationException on first test(1L) call.
         */
        log.info("HashMap: {}", test);
    }

    public static void test(Long l) {
        test.computeIfAbsent(l, t -> {
            // Don't do this on prod! :-D
            test.put(l, t.toString() + "+");
            return l.toString() + "*";
        });
    }
}
