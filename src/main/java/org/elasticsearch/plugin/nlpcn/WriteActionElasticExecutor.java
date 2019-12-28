package org.elasticsearch.plugin.nlpcn;

import java.io.IOException;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.InsertWriteAction;
import org.nlpcn.es4sql.query.WriteAction;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class WriteActionElasticExecutor {

    public static ActionResponse executeInsertAction(InsertWriteAction insertWriteAction) throws SqlParseException {
        return insertWriteAction.explain().get();
    }

    public static Object executeAnyAction(Client client, WriteAction writeAction) throws SqlParseException, IOException {
        if (writeAction instanceof InsertWriteAction) {
            return executeInsertAction((InsertWriteAction) writeAction);
        }
        return null;
    }

}
