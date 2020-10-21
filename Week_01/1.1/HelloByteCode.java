/**
 * 字节码分析
 *
 * @author James
 */
public class HelloByteCode {

    public static void main(String[] args) {
        HelloByteCode helloByteCode = new HelloByteCode();
        helloByteCode.calculate();
        helloByteCode.control();
    }

    public int calculate() {
        int a = 4, b = 6;
        int c = a + b;
        int d = c - b;
        int e = a / d;
        int f = e * b;
        System.out.println(f);
        return f;
    }

    boolean control() {
        int flag = 1;
        if (flag == 0) {
            return false;
        } else if (flag == 1) {
            return true;
        } else {
            throw new RuntimeException("Flag not allowed.");
        }
    }
}
