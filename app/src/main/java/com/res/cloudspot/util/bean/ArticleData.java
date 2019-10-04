package com.res.cloudspot.util.bean;


import java.util.Date;

/**
 * @author ajacker
 */
public class ArticleData {
    public Date date;
    public String title;

    public ArticleData(String title, Date date) {
        this.date = date;
        this.title = title;
    }
}
