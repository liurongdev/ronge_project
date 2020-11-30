package exception;

/**
 *指定具体的异常信息
 */
public class ExceptionDemo2 {

    public static void main(String[] args) throws Exception {
        handleInteger();
    }
    public static void handleInteger() throws Exception{
        Integer age= Integer.parseInt("curry");
        System.out.println("curry.age=" + age);
    }
}
