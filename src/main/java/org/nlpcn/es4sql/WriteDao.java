package org.nlpcn.es4sql;

import java.sql.SQLFeatureNotSupportedException;
import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.ESActionFactory;
import org.nlpcn.es4sql.query.WriteAction;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class WriteDao extends ActionDao {

    public WriteDao(Client client) {
        super(client, false);
    }

    /**
     * Prepare action And transform sql
     * into ES ActionRequest
     *
     * @param sql SQL query to execute.
     * @return ES request
     * @throws SqlParseException
     */
    public WriteAction explain(String sql) throws SqlParseException, SQLFeatureNotSupportedException {
        return ESActionFactory.createWrite(getClient(), sql);
    }

}
