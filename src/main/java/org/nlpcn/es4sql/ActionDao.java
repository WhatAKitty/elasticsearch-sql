package org.nlpcn.es4sql;

import java.sql.SQLFeatureNotSupportedException;
import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.AnyAction;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public abstract class ActionDao {

    private final Client client;
    private final boolean readOnly;

    public ActionDao(Client client, boolean readOnly) {
        this.client = client;
        this.readOnly = readOnly;
    }

    public Client getClient() {
        return client;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public abstract AnyAction explain(String sql) throws SqlParseException, SQLFeatureNotSupportedException;

}
