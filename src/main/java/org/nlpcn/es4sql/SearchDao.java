package org.nlpcn.es4sql;

import java.sql.SQLFeatureNotSupportedException;
import java.util.HashSet;
import java.util.Set;
import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.AnyAction;
import org.nlpcn.es4sql.query.ESActionFactory;


public class SearchDao extends ActionDao {

    private static final Set<String> END_TABLE_MAP = new HashSet<>();

    static {
        END_TABLE_MAP.add("limit");
        END_TABLE_MAP.add("order");
        END_TABLE_MAP.add("where");
        END_TABLE_MAP.add("group");

    }

    public SearchDao(Client client) {
        super(client, true);
    }

    /**
     * Prepare action And transform sql
     * into ES ActionRequest
     *
     * @param sql SQL query to execute.
     * @return ES request
     * @throws SqlParseException
     */
    public AnyAction explain(String sql) throws SqlParseException, SQLFeatureNotSupportedException {
        return ESActionFactory.create(getClient(), sql);
    }


}
