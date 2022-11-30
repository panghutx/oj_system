package cn.young.oj.entity;
//uid，title，level，desc，templateCode，testCode

import lombok.Data;

@Data
public class Topic {
    private Integer uid;
    private String title;
    private String level;
    private String description;
    private String templateCode;
    private String testCode;
}
