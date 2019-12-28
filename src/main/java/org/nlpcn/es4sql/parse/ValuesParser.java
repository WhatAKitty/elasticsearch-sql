package org.nlpcn.es4sql.parse;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.google.common.collect.Lists;
import java.util.List;
import org.nlpcn.es4sql.domain.Value;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class ValuesParser {

    private final SqlParser sqlParser;
    private SQLInsertStatement sqlInsertStatement;
    private SQLUpdateStatement sqlUpdateStatement;

    public ValuesParser(SqlParser sqlParser, SQLInsertStatement sqlInsertStatement) {
        this.sqlParser = sqlParser;
        this.sqlInsertStatement = sqlInsertStatement;
    }

    public List<Value> parseValues() {
        List<SQLExpr> columns = sqlInsertStatement.getColumns();
        if (columns.isEmpty()) {
            throw new RuntimeException("no columns could be found");
        }

        List<SQLExpr> values = sqlInsertStatement.getValues().getValues();
        if (values.size() != columns.size()) {
            throw new RuntimeException("the columns size and values size is not equally");
        }

        int columnSize = columns.size();
        List<Value> result = Lists.newArrayListWithCapacity(columnSize);
        for (int i = 0; i < columnSize; i++) {
            String column = (String) ValueTypeResolver.resolve(columns.get(i));
            Object value = ValueTypeResolver.resolve(values.get(i));

            result.add(new Value(column, value));
        }

        return result;
    }

    private enum ValueTypeResolver {

        SQLIdentifierExpr(sqlExpr -> {
            SQLIdentifierExpr sqlIdentifierExpr = (SQLIdentifierExpr) sqlExpr;
            return sqlIdentifierExpr.getName();
        }),
        SQLIntegerExpr(sqlExpr -> {
            SQLIntegerExpr sqlIntegerExpr = (com.alibaba.druid.sql.ast.expr.SQLIntegerExpr) sqlExpr;
            return sqlIntegerExpr.getNumber();
        }),
        SQLNumberExpr(sqlExpr -> {
            SQLNumberExpr sqlIntegerExpr = (SQLNumberExpr) sqlExpr;
            return sqlIntegerExpr.getNumber();
        }),
        SQLCharExpr(sqlExpr -> {
            SQLCharExpr sqlCharExpr = (SQLCharExpr) sqlExpr;
            return sqlCharExpr.getValue();
        }),
        SQLBooleanExpr(sqlExpr -> {
            SQLBooleanExpr sqlBooleanExpr = (SQLBooleanExpr) sqlExpr;
            return sqlBooleanExpr.getValue();
        }),
        SQLTimestampExpr(sqlExpr -> {
            SQLTimestampExpr sqlTimestampExpr = (SQLTimestampExpr) sqlExpr;
            return sqlTimestampExpr.getLiteral();
        }),
        SQLTextLiteralExpr(sqlExpr -> {
            SQLTextLiteralExpr sqlTextLiteralExpr = (SQLTextLiteralExpr) sqlExpr;
            return sqlTextLiteralExpr.getText();
        });

        public static Object resolve(SQLExpr sqlExpr) {
            String name = sqlExpr.getClass().getSimpleName();
            return valueOf(name).resolver.resolve(sqlExpr);
        }

        private final Resolver resolver;

        ValueTypeResolver(Resolver resolver) {
            this.resolver = resolver;
        }

        @FunctionalInterface
        private interface Resolver {

            Object resolve(SQLExpr sqlExpr);

        }

    }


}
