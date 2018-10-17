package org.saas.qa.util.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey(){
        return DataSourceTypeManager.get();
    }
}
