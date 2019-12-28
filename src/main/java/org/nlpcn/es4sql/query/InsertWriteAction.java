package org.nlpcn.es4sql.query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.elasticsearch.action.index.IndexAction;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.domain.Insert;
import org.nlpcn.es4sql.domain.Value;
import org.nlpcn.es4sql.exception.SqlParseException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/28
 * @description
 **/
public class InsertWriteAction extends WriteAction {

    private final Insert insert;
    private IndexRequestBuilder request;

    public InsertWriteAction(Client client, Insert insert) {
        super(client, insert);
        this.insert = insert;
    }

    @Override
    public SqlElasticRequestBuilder explain() throws SqlParseException {
        this.request = new IndexRequestBuilder(client, IndexAction.INSTANCE);

        setIndicesAndTypes();
        setSource();
        SqlElasticIndexRequestBuilder sqlElasticIndexRequestBuilder = new SqlElasticIndexRequestBuilder(request);
        return sqlElasticIndexRequestBuilder;
    }

    /**
     * Set indices and types to the index request.
     */
    private void setIndicesAndTypes() {

        IndexRequest indexRequest = request.request();
        indexRequest.index(insert.getIndexArr());
        indexRequest.type(insert.getTypeArr());

    }

    private void setSource() {

        List<Value> values = insert.getValues();
        Map<String, Object> source = values.stream().collect(Collectors.toMap(Value::getName, Value::getValue));

        IndexRequest indexRequest = request.request();
        indexRequest.source(source);

    }

}
