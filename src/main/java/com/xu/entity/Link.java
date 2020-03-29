package com.xu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_link")
public class Link {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String url;

    private Integer sort;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
