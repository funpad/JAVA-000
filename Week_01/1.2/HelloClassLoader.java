import java.io.FileInputStream;

/**
 * 测试自定义类加载器
 *
 * @author James
 */
public class HelloClassLoader {

    private static final String CLASS_FILENAME =
            "Hello.xlass";

    public static void main(String[] args) throws Exception {
        byte[] bytes;
        try (FileInputStream fileInputStream = new FileInputStream(CLASS_FILENAME)) {
            bytes = fileInputStream.readAllBytes();
        }

        ClassLoader parentLoader = new HelloClassLoader().getClass().getClassLoader();
        CustomizedClassLoader myLoader = new CustomizedClassLoader(parentLoader, bytes);
        Class<?> klass = myLoader.loadClass("Hello");
        var hello = klass.getMethod("hello");
        Object instance = klass.getDeclaredConstructor().newInstance();

        hello.invoke(instance);
    }
}
