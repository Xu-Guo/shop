package com.xu.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_item")
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 500)
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 500)
    private String imageName;

    private Date lastUpdate;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
