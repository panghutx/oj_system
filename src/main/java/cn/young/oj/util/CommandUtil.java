

package cn.young.oj.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


//1.输入进程名，运行进程
//2.输出标准输出到指定文件
//3.输出标准错误到指定文件
//4.线程等待，返回退出码
public class CommandUtil {
    public static int run(String cmd,String stdoutFile,String errorFile){
        Runtime runtime = Runtime.getRuntime();
        try {
            //1.通过Runtime的exec运行进程
            Process process = runtime.exec(cmd);
            //2.输出标准输出到指定文件
            if(stdoutFile!=null){
                InputStream stdFrom = process.getInputStream();
                FileOutputStream stdTo = new FileOutputStream(stdoutFile);
                while(true){
                    int ch=stdFrom.read();
                    if(ch==-1){
                        break;
                    }
                    stdTo.write(ch);
                }
                stdFrom.close();
                stdTo.close();
            }
            //3.输出标准错误到指定文件
            if(errorFile!=null){
                InputStream errorFrom = process.getErrorStream();
                FileOutputStream errorTo = new FileOutputStream(errorFile);
                while(true){
                    int ch = errorFrom.read();
                    if(ch==-1){
                        break;
                    }
                    errorTo.write(ch);
                }
                errorFrom.close();
                errorTo.close();
            }
            //4.进程等待
            int exitCode = process.waitFor();
            return exitCode;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static void main(String[] args) {
        CommandUtil.run("javac","stdout.txt","error.txt");
    }
}

