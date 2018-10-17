package org.saas.qa.util.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//通过ThreadLocal来保存每个线程选择哪个数据源的标志(key)
public class DataSourceTypeManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceTypeManager.class);
    //类初始化过程？？？
    private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>(){
        //ThreadLocal.initialValue: ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
        @Override
        protected DataSources initialValue(){
            LOGGER.info(" ==> 执行initialValue方法...");
            return DataSources.DEFAULT;
        }
    };

    public static  DataSources get(){
        LOGGER.debug(" == > 获取DataSource ==> " + dataSourceTypes.get());
        LOGGER.debug("ThreadLocal是否为 null ==> " + (null == DataSourceTypeManager.dataSourceTypes.get()));
        return dataSourceTypes.get();
    }

    public static void set(DataSources dataSourceType){
        LOGGER.info(" ==> setDataSource");
        dataSourceTypes.set(dataSourceType);
        LOGGER.debug(" ==> set后的DataSource ==> " + dataSourceTypes.get());
    }

    public static void reset(){
        LOGGER.info(" ==> 执行reset方法...");
        dataSourceTypes.set(DataSources.DEFAULT);
    }
}
