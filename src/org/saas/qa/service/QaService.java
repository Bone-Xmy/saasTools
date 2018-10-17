package org.saas.qa.service;

import java.util.List;

import org.saas.qa.domain.*;
import org.saas.qa.util.tag.PageModel;

public interface QaService {
	/**
	 * 用户登录
	 * @param loginname
	 * @param password
	 * @return User对象
	 */
	User login(String loginname,String password);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 */
	User findUserById(Integer id);
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 */
	List<User> findUser(User user,PageModel pageModel);
	/**
	 * 根据id删除用户
	 * @param id
	 */
	void removeUserById(Integer id);
	/**
	 * 修改用户
	 * @param User对象
	 */
	void modifyUser(User user);
	/**
	 * 添加用户
	 * @param User 用户对象
	 */
	void addUser(User user);
	////
	/**
	 * 获得所有订单
	 * @param orderFood 查询条件
	 * @param pageModel 分页对象
	 * @return OrderFood对象的List集合
	 */
	List<OrderFood> findOrderFood(OrderFood orderFood,PageModel pageModel);

	/**
	 * 根据saasOrderKey删除菜品
	 * @param saasOrderKey
	 */
	void removeOrderFoodBySaasOrderKey(String saasOrderKey);
	/**
	 * 根据saasOrderKey查询员菜品
	 * @param saasOrderKey
	 * @return 菜品对象
	 */
	OrderFood findOrderFoodBySaasOrderKey(String saasOrderKey);
	/**
	 * 添加菜品
	 * @param orderFood 菜品对象
	 */
	void addOrderFood(OrderFood orderFood);
	/**
	 * 修改菜品
	 * @param orderFood 菜品对象
	 */
	void modifyOrderFood(OrderFood orderFood);
	////
	/**
	 * 获得所有账单，分页查询
	 * @return OrderMaster对象的List集合
	 */
	List<OrderMaster> findOrderMaster(OrderMaster orderMaster,PageModel pageModel);
	/**
	 * 获得所有账单
	 * @return OrderMaster对象的List集合
	 */
	List<OrderMaster> findAllOrderMaster();
	/**
	 * 根据saasOrderKey删除账单
	 * @param saasOrderKey
	 */
	void removeOrderMasterBySaasOrderKey(String saasOrderKey);
	/**
	 * 添加账单
	 * @param orderMaster 账单对象
	 */
	void addOrderMaster(OrderMaster orderMaster);
	/**
	 * 根据saasOrderKey查询账单
	 * @param saasOrderKey
	 * @return 账单对象
	 */
	OrderMaster findOrderMasterBySaasOrderKey(String saasOrderKey);
	/**
	 * 修改账单
	 * @param orderMaster 账单对象
	 */
	void modifyOrderMaster(OrderMaster orderMaster);
	////
	/**
	 * 获得所有支付明细
	 * @param orderPay 查询条件
	 * @param pageModel 分页对象
	 * @return orderPay对象的List集合
	 */
	List<OrderPay> findOrderPay(OrderPay orderPay,PageModel pageModel);
	/**
	 * 根据saasOrderKey删除支付明细
	 * @param saasOrderKey
	 */
	void removeOrderPayBySaasOrderKey(String saasOrderKey);
	/**
	 * 根据saasOrderKey查询支付明细
	 * @param saasOrderKey
	 * @return 支付明细对象
	 */
	OrderPay findOrderPayBySaasOrderKey(String saasOrderKey);
	/**
	 * 添加支付明细
	 * @param orderPay支付明细对象
	 */
	void addOrderPay(OrderPay orderPay);
	/**
	 * 修改员工
	 * @param orderPay支付明细对象
	 */
	void modifyOrderPay(OrderPay orderPay);
	///
	/**
	 * 查询异常账单
	 */
	List<AbnormalOrder> findAllAbnormalOrder();
}
