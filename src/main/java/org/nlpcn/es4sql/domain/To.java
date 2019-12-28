package org.nlpcn.es4sql.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class To {

    private String index;
    private String type;
    private String alias;

    /**
     * Extract index and type from the 'from' string
     *
     * @param to The part after the FROM keyword.
     */
    public To(String to) {
        if (to.startsWith("<")) {
            index = to;
            if (!to.endsWith(">")) {
                int i = to.lastIndexOf('/');
                if (-1 < i) {
                    index = to.substring(0, i);
                    type = to.substring(i + 1);
                }
            }
            return;
        }
        String[] parts = to.split("/");
        this.index = parts[0].trim();
        if (parts.length == 2) {
            this.type = parts[1].trim();
        }
    }

    public To(String to, String alias) {
        this(to);
        this.alias = alias;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
