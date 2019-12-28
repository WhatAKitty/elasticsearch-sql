package org.nlpcn.es4sql.query;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class SqlElasticIndexRequestBuilder implements SqlElasticRequestBuilder {

    private final IndexRequestBuilder indexRequestBuilder;

    public SqlElasticIndexRequestBuilder(IndexRequestBuilder indexRequestBuilder) {
        this.indexRequestBuilder = indexRequestBuilder;
    }

    @Override
    public ActionRequest request() {
        return indexRequestBuilder.request();
    }

    @Override
    public String explain() {
        return null;
    }

    @Override
    public ActionResponse get() {
        return indexRequestBuilder.get();
    }

    @Override
    public ActionRequestBuilder getBuilder() {
        return indexRequestBuilder;
    }

}
