package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *在 finally 中关闭文件资源，或者使用try-with-resource语句处理文件资源
 */
public class ExceptionDemo1 {

    public static final Logger log= LoggerFactory.getLogger(ExceptionDemo1.class);
    public static void main(String[] args) {

    }


    public static void handleSourceFile(){
        FileInputStream inputStream=null;
        try {
            File file=new File("content.txt");
            inputStream=new FileInputStream(file);
            //操作文件内容

            //关闭流
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void closeInFinally(){
        FileInputStream inputStream=null;
        try {
            File file=new File("content.txt");
            inputStream=new FileInputStream(file);
            //操作文件内容
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void autoCloseResource(){
        File file=new File("content.txt");
        try (FileInputStream inputStream=new FileInputStream(file)){

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
