package cn.young.oj.compile;

import cn.young.oj.util.CommandUtil;
import cn.young.oj.util.FileUit;

import java.io.File;
import java.util.UUID;

public class Task {
    //输入Question类，即提交的代码，返回Answer，即执行结果信息。
    private String WORK_DIR = null;
    // 约定代码的类名
    private String CLASS = null;
    // 约定要编译的代码文件名.
    private String CODE = null;
    // 约定存放编译错误信息的文件名
    private String COMPILE_ERROR = null;
    // 约定存放运行时的标准输出的文件名
    private String STDOUT = null;
    // 约定存放运行时的标准错误的文件名
    private String STDERR = null;

    public Task(){
        String workingDir = System.getProperty("user.dir");
        WORK_DIR = workingDir+File.separator+"tmp"+File.separator+UUID.randomUUID().toString()+File.separator;
        CLASS = "Solution";
        CODE = WORK_DIR+"Solution.java";
        COMPILE_ERROR = WORK_DIR+"compileError.txt";
        STDOUT = WORK_DIR+"stdout.txt";
        STDERR = WORK_DIR+"stderr.txt";
    }
    public Answer compileAndRun(Question question){
        Answer answer = new Answer();
        //1.创建临时目录文件
        File file = new File(WORK_DIR);
        if (!file.exists()){
            final boolean mkdirs = file.mkdirs();
            System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            System.out.println(mkdirs);
        }
        //2.代码编译
        //-d 指定生成的临时文件的位置
        FileUit.writeFile(CODE,question.getCode());
        String cmd = String.format("javac -encoding utf8 %s -d %s",CODE,WORK_DIR);
        CommandUtil.run(cmd,null,COMPILE_ERROR);
        //判断编译是否错误
        String compile = FileUit.readFile(COMPILE_ERROR);
        if(!compile.equals("")){
            //文件编译错误
            answer.setError(1);
            answer.setReason(FileUit.readFile(COMPILE_ERROR));
            return answer;
        }
        //3.运行代码
        String runCmd = String.format("java -classpath %s %s",WORK_DIR,CLASS);
        CommandUtil.run(runCmd,STDOUT,STDERR);
        String stderr = FileUit.readFile(STDERR);
        if(!stderr.equals("")){
            //文件运行错误
            answer.setError(2);
            answer.setReason(stderr);
            return answer;
        }
        //4.编译运行正确，获得执行结果
        answer.setError(0);
        String stdout = FileUit.readFile(STDOUT);
        answer.setStdout(stdout);
        return answer;
    }
    public static void main(String[] args) {
        Task task = new Task();
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
    }
}
