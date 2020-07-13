package instance.of.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppInstanceof {
    private static final Logger log = LoggerFactory.getLogger(AppInstanceof.class);
    public static void main(String[] args) throws Exception {
        ClassLoader c1 = new CustomClassLoader();
        Class<?> class1 = c1.loadClass("instance.of.test.LoadClass");

        Object instance1 = class1.newInstance();
        if (new LoadClass() instanceof LoadClass) {
            log.error("Simple test - it's OK.");
        }
        if (instance1 instanceof LoadClass) {
            log.error("Fail, instanceof must be false.");
        } else {
            log.info("instanceof is false, and it's OK");
            log.info("instance1.class={}", instance1.getClass());
            log.info("instance1.class.loader={}", instance1.getClass().getClassLoader());
            log.info("compared with={}", LoadClass.class);
            log.info("compared with class.loader={}", LoadClass.class.getClassLoader());
        }
    }
}
