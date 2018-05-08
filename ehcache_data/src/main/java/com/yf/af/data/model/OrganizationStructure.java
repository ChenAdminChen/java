package com.yf.af.data.model;

import java.io.Serializable;

/**
 * Created by xiemingquan on 2017/8/22.
 */
public class OrganizationStructure implements Serializable{

    private Integer id;
    private Integer parent;
    private Integer type;
    private String hs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }
}
