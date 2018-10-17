package org.saas.qa.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.saas.qa.domain.User;
import org.saas.qa.util.source.DataSourceTypeManager;
import org.saas.qa.util.source.DataSources;
import org.saas.qa.util.common.SaasToolsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

@Aspect
@Component
@Order(0)
public class DataSourceInterceptor {
    @Autowired
    private HttpSession session;

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceInterceptor.class);
    //为什么写为 public org.saas.qa.dao.*.*(..)之后会报错 ==>public是可以省略的，但是前面还有一个返回值是不能返回的
    //User的信息由于不用依赖与具体的表，所以直接使用固定的MySql数据库
    //org.saas.qa.service.impl由于需要用到dao对象，所以也需要AspectJ进行切面处理
    @Pointcut("execution(* org.saas.qa.dao.UserDao.*(..)) || execution(* org.saas.qa.service.impl.*.*User*(..)) || execution(* org.saas.qa.service.impl.*.login(..))")
    public void userDataSourceDispatch(){}

    @Pointcut("execution(* org.saas.qa.dao.*.*(..)) || execution(* org.saas.qa.service.impl.*.*(..))! userDataSourceDispatch()")
    public void dataSourceDispatch(){}

    @Before("userDataSourceDispatch()")
    public void beforeUserDataSource(){
        LOGGER.debug(" ==> DataSource为DEFAULT");
        DataSourceTypeManager.set(DataSources.DEFAULT);
    }

    @Before("dataSourceDispatch()")
    public void beforeOrderDataSource(JoinPoint jp){
        User user = (User)session.getAttribute(SaasToolsConstants.USER_SESSION);
        LOGGER.info("开始DataSourceInterceptor...");
        if(user != null){
            LOGGER.debug(" ==> user的登录名为" + user.getLoginname());
        }
        //login的时候user为null，此时通过该方法设置DataSource
        if(user == null){
            LOGGER.debug(" ==> 获取到了user信息，user为null(说明没有登录成功！！！)，需要设置DataSource为Default");
            DataSourceTypeManager.set(DataSources.DEFAULT);
        }
        else if(user.getLoginname().equals("admin")){
            LOGGER.info("DataSource为ADMIN");
            DataSourceTypeManager.set(DataSources.ADMIN);
        }
        else if(user.getLoginname().equals("xmy")){
            LOGGER.info("DataSource为XMY");
            DataSourceTypeManager.set(DataSources.XMY);
        }
    }
}
