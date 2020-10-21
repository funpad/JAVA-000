/**
 * 带有解码功能的自定义类加载器
 *
 * @author James
 */
class CustomizedClassLoader extends ClassLoader {

    private static final String NAME = "James";

    private byte[] classBytes;

    public CustomizedClassLoader(ClassLoader parent, byte[] classBytes) {
        super(parent);
        decode(classBytes);
        this.classBytes = classBytes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (classBytes == null) {
            return super.findClass(name);
        }
        final Class<?> klass = super.defineClass(name, classBytes, 0, classBytes.length);
        classBytes = null;
        return klass;
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * 解码：取反恢复原样。
     *
     * @param bytes 被这样处理过：255 - {字节} 的class文件
     */
    private void decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (~bytes[i]);
        }
    }
}
