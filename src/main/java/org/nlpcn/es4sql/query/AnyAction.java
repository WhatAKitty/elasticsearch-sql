package org.nlpcn.es4sql.query;

import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.exception.SqlParseException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public abstract class AnyAction {

    protected Client client;

    public AnyAction(Client client) {
        this.client = client;
    }

    /**
     * Prepare the request, and return ES request.
     *
     * @return ActionRequestBuilder (ES request)
     * @throws SqlParseException
     */
    public abstract SqlElasticRequestBuilder explain() throws SqlParseException;

}
