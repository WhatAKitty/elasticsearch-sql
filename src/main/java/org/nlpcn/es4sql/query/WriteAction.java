package org.nlpcn.es4sql.query;

import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.domain.Write;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public abstract class WriteAction extends AnyAction {

    protected final org.nlpcn.es4sql.domain.Write write;

    public WriteAction(Client client, Write write) {
        super(client);
        this.write = write;
    }

}
