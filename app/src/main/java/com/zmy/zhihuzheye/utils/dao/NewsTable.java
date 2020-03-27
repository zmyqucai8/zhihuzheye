package com.zmy.zhihuzheye.utils.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Description: 新闻表
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-27 09:53
 * @Notice 无
 */
@Entity
public class NewsTable {

    @Id
    private Long id;
    @Index(unique = true)
    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String thumbnail_pic_s;
    private String author_name;
    private String url;
    private long time;

    @Generated(hash = 1081144428)
    public NewsTable() {
    }
    @Generated(hash = 218135282)
    public NewsTable(Long id, String uniquekey, String title, String date,
            String category, String thumbnail_pic_s, String author_name, String url,
            long time) {
        this.id = id;
        this.uniquekey = uniquekey;
        this.title = title;
        this.date = date;
        this.category = category;
        this.thumbnail_pic_s = thumbnail_pic_s;
        this.author_name = author_name;
        this.url = url;
        this.time = time;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUniquekey() {
        return this.uniquekey;
    }
    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getThumbnail_pic_s() {
        return this.thumbnail_pic_s;
    }
    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
    public String getAuthor_name() {
        return this.author_name;
    }
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }

}
