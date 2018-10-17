package org.saas.qa.controller;


import java.util.List;
import org.saas.qa.domain.OrderFood;
import org.saas.qa.domain.OrderMaster;
import org.saas.qa.domain.OrderPay;
//import org.saas.qa.domain.Job;
import org.saas.qa.service.QaService;
import org.saas.qa.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OrderFoodController {
	/**
	 * 自动注入qaService
	 * */
	@Autowired
	@Qualifier("qaService")
	private QaService qaService;
			
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param String job_id 职位编号
	 * @param String saasOrderKey 部门编号
	 * @param orderFood 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/orderFood/selectOrderFoodDetail")
	 public String selectOrderFood(Integer pageIndex,
			 String saasOrderKey,
			 @ModelAttribute OrderFood orderFood,
			 Model model){
		// 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(saasOrderKey, orderFood);
		// 创建分页对象
		PageModel pageModel = new PageModel();
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		// 查询账单信息 ，用于模糊查询
		List<OrderMaster> orderMasters = qaService.findAllOrderMaster();
		// 查询菜品信息    
		List<OrderFood> orderFoods = qaService.findOrderFood(orderFood,pageModel);
		// 设置Model数据
		model.addAttribute("orderFoods", orderFoods);
		model.addAttribute("orderMasters", orderMasters);
		model.addAttribute("pageModel", pageModel);
		//System.out.println("/orderfood/orderfood.jsp");
		// 返回菜品信息页面
		return "/orderfood/orderfood";
		
	}
	
	/**
	 * 处理添加员工请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param String job_id 职位编号
	 * @param String saasOrderKey 部门编号
	 * @param orderFood orderFood 接收添加参数
	 * @param ModelAndView mv 
	 * */
	/*
	@RequestMapping(value="/orderFood/addOrderFood")
	 public ModelAndView addOrderFood(
			 String flag,
			 Integer job_id,Integer saasOrderKey,
			 @ModelAttribute orderFood orderFood,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 查询职位信息
			List<Job> jobs = qaService.findAllJob();
			// 查询部门信息 
			List<OrderMaster> orderMasters = qaService.findAllOrderMaster();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("orderMasters", orderMasters);
			// 返回添加员工页面
			mv.setViewName("orderFood/showAddOrderFood");
		}else{
			// 判断是否有关联对象传递，如果有，创建关联对象
			this.genericAssociation(job_id, saasOrderKey, orderFood);
			// 添加操作
			qaService.addOrderFood(orderFood);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/orderFood/selectOrderFood");
		}
		// 返回
		return mv;
		
	}*/
	
	/**
	 * 处理删除员工请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	/*
	@RequestMapping(value="/orderFood/removeOrderFood")
	 public ModelAndView removeOrderFood(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			qaService.removeOrderFoodById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
//		mv.setView(new RedirectView("/qaapp/orderFood/selectOrderFood"));
//		mv.setViewName("forward:/orderFood/selectOrderFood");
		mv.setViewName("redirect:/orderFood/selectOrderFood");
		// 返回ModelAndView
		return mv;
	}*/
	
	/**
	 * 处理修改员工请求
	 * @param String flag 标记，1表示跳转到修改页面，2表示执行修改操作
	 * @param String job_id 职位编号
	 * @param String saasOrderKey 部门编号
	 * @param orderFood orderFood  要修改员工的对象
	 * @param ModelAndView mv
	 * */
	/*
	@RequestMapping(value="/orderFood/updateorderFood")
	 public ModelAndView updateorderFood(
			 String flag,
			 Integer job_id,Integer saasOrderKey,
			 @ModelAttribute orderFood orderFood,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询员工
			orderFood target = qaService.findOrderFoodById(orderFood.getId());
			// 需要查询职位信息 
			List<Job> jobs = qaService.findAllJob();
			// 需要查询部门信息 
			List<OrderMaster> orderMasters = qaService.findAllOrderMaster();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("orderMasters", orderMasters);
			mv.addObject("orderFood", target);
			// 返回修改员工页面
			mv.setViewName("orderFood/showUpdateorderFood");
		}else{
			// 创建并封装关联对象
			this.genericAssociation(job_id, saasOrderKey, orderFood);
			System.out.println("updateorderFood -->> " + orderFood);
			// 执行修改操作
			qaService.modifyorderFood(orderFood);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/orderFood/selectOrderFood");
		}
		// 返回
		return mv;
	}*/
	
	/**
	 * 由于账单主信息在orderFood中是对象关联映射，
	 * 所以不能直接接收参数，需要创建OrderMaster对象
	 * */
	
	private void genericAssociation(String saasOrderKey,
		OrderFood orderFood){
		if(saasOrderKey != null){
			OrderMaster orderMaster = new OrderMaster();
			orderMaster.setSaasOrderKey(saasOrderKey);
			orderFood.setOrderMaster(orderMaster);
		}
	}
}
