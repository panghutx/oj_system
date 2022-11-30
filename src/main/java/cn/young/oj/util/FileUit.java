package cn.young.oj.util;

import java.io.*;
import java.util.Scanner;

//读写文件
public class FileUit {

    //读文件
    public static String readFile(String filename){
        StringBuilder stringBuilder = new StringBuilder();
        try(Reader reader = new FileReader(filename)) {
            Scanner scanner = new Scanner(reader);

            while(scanner.hasNext()){
                final String line = scanner.nextLine();
                stringBuilder.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //写文件
    public static void writeFile(String filename,String content){
        try(Writer writer = new FileWriter(filename)) {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
