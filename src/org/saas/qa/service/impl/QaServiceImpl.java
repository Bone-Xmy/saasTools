package org.saas.qa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.saas.qa.dao.*;
import org.saas.qa.domain.*;
import org.saas.qa.service.QaService;
import org.saas.qa.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Qa系统服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("qaService")
public class QaServiceImpl implements QaService{
	/**
	 * 自动注入持久层Dao对象
	 */
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderMasterDao orderMasterDao;
	@Autowired
	private OrderFoodDao orderFoodDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private AbnormalOrderDao abnormalOrderDao;
	/*******************用户服务接口实现*******************/
	/**
	 * QaServiceImpl接口login方法实现
	 * @see {QaService}
	 */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname,String password) {
		System.out.println("QaServiceImpl login -- >>");
		return userDao.selectByUsernameAndPassword(loginname, password);
	}
	/**
	 * QaServiceImpl接口findUser方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user,PageModel pageModel){
		/** 当前需要分页的总数据条数 */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		System.out.println("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<User> users = userDao.selectByPage(params);
		return users;
	}
	/**
	 * QaServiceImpl接口findUserById方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	/**
	 * QaServiceImpl接口removeUserById方法实现
	 * @see { QaService }
	 */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
	}
	/**
	 * QaServiceImpl接口addUser方法实现
	 * @see { QaService }
	 */
	@Override
	public void addUser(User user) {
		userDao.save(user);
	}
	/**
	 * QaServiceImpl接口modifyUser方法实现
	 * @see { QaService}
	 */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
	}
	/*******************账单接口实现*******************/
	@Override
	@Transactional(readOnly=true)
	public List<OrderMaster> findAllOrderMaster(){
		return orderMasterDao.selectAllOrder();
	}
	@Transactional(readOnly=true)
	@Override
	public List<OrderMaster> findOrderMaster(OrderMaster orderMaster,PageModel pageModel){
		/** 当前需要分页的总数据条数 */
		Map<String,Object> params = new HashMap<>();
		params.put("orderMaster", orderMaster);
		int recordCount = orderMasterDao.count(params);
		System.out.println("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0) {
			/** 开始分页查询数据，查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<OrderMaster> orderMasters = orderMasterDao.selectByPage(params);
		return orderMasters;
	}
	/**
	 * QaServiceImpl接口removeOrderMasterBySaasOrderKey方法实现
	 * @see { QaService }
	 */
	@Override
	public void removeOrderMasterBySaasOrderKey(String saasOrderKey) {
		orderMasterDao.deleteBySaasOrderKey(saasOrderKey);
	}
	/**
	 * QaServiceImpl接口addOrderMaster方法实现
	 * @see { QaService }
	 */
	@Override
	public void addOrderMaster(OrderMaster orderMaster) {
		orderMasterDao.save(orderMaster);
	}
	/**
	 * QaServiceImpl接口findOrderMasterBySaasOrderKey方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public OrderMaster findOrderMasterBySaasOrderKey(String saasOrderKey) {
		return orderMasterDao.selectBySaasOrderKey(saasOrderKey);
	}
	/**
	 * QaServiceImpl接口modifyOrderMaster实现
	 * @see { QaService }
	 */
	@Override
	public void modifyOrderMaster(OrderMaster orderMaster) {
		orderMasterDao.update(orderMaster);
	}
	/*******************菜品服务接口实现*******************/
	/**
	 * QaService接口findOrderFood方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public List<OrderFood> findOrderFood(OrderFood orderFood,PageModel pageModel){
		/** 当前需要分页的总数据条数 */
		Map<String,Object> params = new HashMap<>();
		System.out.println("qaServiceImpl orderFood  -- >> " + orderFood);
		params.put("orderFood", orderFood);
		int recordCount = orderFoodDao.count(params);
		System.out.println("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0 ) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<OrderFood> orderFoods = orderFoodDao.selctByPage(params);
		return orderFoods;
	}

	/**
	 * QaService接口removeOrderFoodBySaasOrderKey方法实现
	 * @see { QaService }
	 */
	@Override
	public void removeOrderFoodBySaasOrderKey(String saasOrderKey) {
		orderFoodDao.deleteBySaasOrderKey(saasOrderKey);
	}
	/**
	 * QaService接口findOrderFoodBySaasOrderKey方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public OrderFood findOrderFoodBySaasOrderKey(String saasOrderKey) {
		return orderFoodDao.selectBySaasOrderKey(saasOrderKey);
	}
	/**
	 * QaService接口addOrderFood方法实现
	 * @see { QaService }
	 */
	@Override
	public void addOrderFood(OrderFood orderFood) {
		orderFoodDao.save(orderFood);
	}
	/**
	 * QaService接口modifyOrderFood方法实现
	 * @see { QaService }
	 */
	@Override
	public void modifyOrderFood(OrderFood orderFood) {
		orderFoodDao.update(orderFood);
	}
	/*******************支付服务接口实现*******************/
	/**
	 * QaService接口findOrderPay方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public List<OrderPay> findOrderPay(OrderPay orderPay,PageModel pageModel){
		/** 当前需要分页的总数据条数 */
		Map<String,Object> params = new HashMap<>();
		params.put("orderPay", orderPay);
		int recordCount = orderPayDao.count(params);
		System.out.println("recordCount -->> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<OrderPay> orderPays = orderPayDao.selectByPage(params);
		return orderPays;
	}
	/**
	 * QaService接口removeOrderPayBySaasOrderKey方法实现
	 * @see { QaService }
	 */
	@Override
	public void removeOrderPayBySaasOrderKey(String saasOrderKey) {
		orderPayDao.deleteBySaasOrderKey(saasOrderKey);
	}
	/**
	 * QaService接口findBySaasOrderKey方法实现
	 * @see { QaService }
	 */
	@Transactional(readOnly=true)
	@Override
	public OrderPay findOrderPayBySaasOrderKey(String saasOrderKey) {
		return orderPayDao.selectBySaasOrderKey(saasOrderKey);
	}
	/**
	 * QaService接口addOrderPay方法实现
	 * @see { QaService }
	 */
	@Override
	public void addOrderPay(OrderPay orderPay) {
		orderPayDao.save(orderPay);
	}
	/**
	 * QaService接口modifyOrderPay方法实现
	 * @see { QaService }
	 */
	@Override
	public void modifyOrderPay(OrderPay orderPay) {
		orderPayDao.update(orderPay);
	}
	/*******************异常账单服务接口实现*******************/
	/**
	 * QaService接口selectAllAbnormalOrder方法实现
	 * @see { QaService }
	 */
	@Override
	public List<AbnormalOrder> findAllAbnormalOrder(){
		return abnormalOrderDao.selectAllAbnormalOrder();
	}
}