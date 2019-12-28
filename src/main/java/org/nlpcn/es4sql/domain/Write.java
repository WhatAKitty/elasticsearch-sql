package org.nlpcn.es4sql.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class Write {

    private List<Value> values;
    private final To to;

    public Write(To to) {
        this.to = to;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public To getTo() {
        return to;
    }

    /**
     * Get the indexes the query refer to.
     *
     * @return the index names
     */
    public String getIndexArr() {
        return to.getIndex();
    }

    /**
     * Get the types the query refer to.
     *
     * @return he type names
     */
    public String getTypeArr() {
        return to.getType();
    }

}
