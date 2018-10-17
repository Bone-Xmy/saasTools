package org.saas.qa.controller;

import java.util.List;

import org.saas.qa.domain.OrderMaster;
import org.saas.qa.service.QaService;
import org.saas.qa.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderMasterController {
	/**
	 * 自动注入qaService
	 */
	@Autowired
	@Qualifier("qaService")
	private QaService qaService;
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param itemID 
	 * @param saasOrderKey
	 * @param orderMaster
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/orderMaster/selectOrderMasterDetail")
	public String selectOrderMaster(Integer pageIndex,
			@ModelAttribute OrderMaster orderMaster,
			Model model) {
		//创建分页对象
		PageModel pageModel = new PageModel();
		//如果参数pageIndex不为null,设置pageIndex，即显示第几页
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		//查询账单信息
		List<OrderMaster> orderMasters = qaService.findOrderMaster(orderMaster,pageModel);
		model.addAttribute("orderMasters",orderMasters);
		model.addAttribute("pageModel", pageModel);
		return "/ordermaster/ordermaster";
	}
}

