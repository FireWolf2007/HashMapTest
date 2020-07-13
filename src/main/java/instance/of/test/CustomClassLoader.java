package instance.of.test;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomClassLoader extends ClassLoader {
    private final Logger log = LoggerFactory.getLogger(CustomClassLoader.class);

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        log.info("loadClass '{}'", name);
        if (name.startsWith("instance.of.")) {
            log.info("Loading Class using CustomClassLoader");
            return getClass(name);
        }
        return super.loadClass(name);
    }

    private Class<?> getClass(String name) {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;
        try {
            b = loadClassFileData(file);
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte[] buff = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
