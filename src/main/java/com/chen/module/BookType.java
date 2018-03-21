package com.chen.module;

import java.io.Serializable;

/**
 * Created by chen on 2018/3/21.
 */
public class BookType implements Serializable{
    private String TypeName;

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public BookType(String typeName) {
        TypeName = typeName;
    }
}
