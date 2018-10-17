package org.saas.qa.dao;

import org.apache.ibatis.annotations.*;
import org.saas.qa.domain.User;
import org.saas.qa.dao.provider.UserDynaSqlProvider;
import static org.saas.qa.util.common.SaasToolsConstants.USERTABLE;

import java.util.List;
import java.util.Map;

public interface UserDao {
	//根据登录名和密码查询员工
	@Select("select * from " + USERTABLE + " where loginname = #{loginname} and password = #{password}")
	//@Param("uerName")注解表示给该注解后面的变了取一个参数名称，对应@Select注解中的#{userName}
	User selectByUsernameAndPassword(@Param("loginname") String loginname,@Param("password") String password);
	//根据id查询用户
	@Select("select * from " + USERTABLE + " where id = #{id}")
	User selectById(Integer id);
	//根据id删除用户
	@Delete("delete from " + USERTABLE + "where id = #{id}")
	void deleteById(Integer id);
	//动态修改用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);
	//动态查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	List<User> selectByPage(Map<String,Object> params);
	//根据参数查询用户总数
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);
}
