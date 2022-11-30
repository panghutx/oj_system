package cn.young.oj.compile;

import lombok.Data;

@Data
public class Answer {
    //错误码 0->编译运行通过 1->编译错误 2->运行错误
    private int error;
    //错误信息
    private String reason;
    //标准输出结果
    private String stdout;
    //标准错误结果
    private String stderr;
}
