package org.nlpcn.es4sql;

import java.sql.SQLFeatureNotSupportedException;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.SqlElasticRequestBuilder;
import org.nlpcn.es4sql.query.WriteAction;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class InsertTest {

    @Before
    public void before() throws Exception {
        MainTestSuite.setUp();
    }

    @Test
    public void test() throws SQLFeatureNotSupportedException, SqlParseException {
        WriteDao writeDao = MainTestSuite.getWriteDao();
        WriteAction writeAction = writeDao.explain("insert twitter/doc(id,name,twitter#,date) values(1234567,'test','sql,is,awesome1;','2019-12-28')");
        SqlElasticRequestBuilder explain = writeAction.explain();
        ActionResponse actionResponse = explain.get();
        System.out.println(actionResponse);
    }

    @Test
    public void test2() {
        IndexRequest indexRequest = new IndexRequest();

        Assert.assertTrue(indexRequest instanceof IndexRequest);
    }

}
