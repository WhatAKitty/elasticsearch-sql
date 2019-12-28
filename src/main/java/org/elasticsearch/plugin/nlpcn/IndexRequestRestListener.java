package org.elasticsearch.plugin.nlpcn;

import java.util.Map;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.action.RestBuilderListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/12/29
 * @description
 **/
public class IndexRequestRestListener extends RestBuilderListener<IndexResponse> {

    private final Map<String, String> params;

    public IndexRequestRestListener(RestChannel channel, Map<String, String> params) {
        super(channel);
        this.params = params;
    }

    @Override
    public RestResponse buildResponse(IndexResponse indexResponse, XContentBuilder builder) throws Exception {
        indexResponse.toXContent(builder, new ToXContent.DelegatingMapParams(params, channel.request()));
        return new BytesRestResponse(getStatus(indexResponse), builder);
    }

    private RestStatus getStatus(IndexResponse response) {
        return RestStatus.valueOf(response.getResult().name().toUpperCase());
    }

}
