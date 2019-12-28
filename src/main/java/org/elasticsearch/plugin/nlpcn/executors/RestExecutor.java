package org.elasticsearch.plugin.nlpcn.executors;

import java.util.Map;
import org.elasticsearch.client.Client;
import org.elasticsearch.rest.RestChannel;
import org.nlpcn.es4sql.query.QueryAction;
import org.nlpcn.es4sql.query.WriteAction;

/**
 * Created by Eliran on 26/12/2015.
 */
public interface RestExecutor {
    public void execute(Client client, Map<String, String> params, QueryAction queryAction, RestChannel channel) throws Exception;

    public String execute(Client client, Map<String, String> params, QueryAction queryAction) throws Exception;

    public void execute(Client client, Map<String, String> params, WriteAction writeAction, RestChannel channel) throws Exception;

    public String execute(Client client, Map<String, String> params, WriteAction writeAction) throws Exception;
}
