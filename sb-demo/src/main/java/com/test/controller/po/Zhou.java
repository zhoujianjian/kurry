package com.test.controller.po;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @ClassName: Zhou
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/31$ 17:37$
 * @Version: 1.0
 */
@Document(indexName = "zhou",type = "jian")
public class Zhou implements Serializable{

    private Long id;

    private String name;

    private String desc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
