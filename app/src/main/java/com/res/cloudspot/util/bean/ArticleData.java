package com.res.cloudspot.util.bean;


import java.util.Date;

/**
 * @author ajacker
 */
public class ArticleData {
    public Date createTime;
    public String title;

    public ArticleData(String title, Date createTime) {
        this.createTime = createTime;
        this.title = title;
    }
}
