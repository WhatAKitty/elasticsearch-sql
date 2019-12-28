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
public final class ActionDaoFactory {

    public static String getType(String sql) {
        sql = sql.replaceAll("\n", " ");
        String firstWord = sql.substring(0, sql.indexOf(' '));
        return firstWord.toUpperCase();
    }

    public static ActionDao createActionDao(Client client, String sql) {
        String type = getType(sql);

        switch (type) {
            case "SELECT":
            case "DELETE":
            case "SHOW":
                return new SearchDao(client);
            case "INSERT":
                return new WriteDao(client);
            default:
                throw new RuntimeException("not supported");
        }
    }

    public static AnyAction explain(Client client, String sql) throws SQLFeatureNotSupportedException, SqlParseException {
        ActionDao actionDao = createActionDao(client, sql);
        return actionDao.explain(sql);
    }

}
