package org.saas.qa.controller;

import java.util.List;

import org.saas.qa.domain.*;
import org.saas.qa.service.QaService;
import org.saas.qa.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderPayController {
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
	 * @param String orderKey 部门编号
	 * @param orderFood 模糊查询参数
	 * @param Model model
	 * */
	
	@RequestMapping(value="/orderPay/selectOrderPayDetail")
	public String selectOrderPay(Integer pageIndex,
			String rowId,String saasOrderKey,
			@ModelAttribute OrderPay orderPay,Model model) {
		
		// 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(saasOrderKey, orderPay);

		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		//支付明细查询 
		List<OrderPay> orderPays = qaService.findOrderPay(orderPay, pageModel);
		model.addAttribute("orderPays",orderPays);
		model.addAttribute("pageModel",pageModel);
		return "/orderpay/orderpay";
	}


	/* 由于账单主信息在orderPay中是通过关联对象映射的，
	 * 所以不能直接接收参数，需要先创建orderMaster对象
	 */

	private void genericAssociation(String saasOrderKey,
		OrderPay orderPay){
		if(saasOrderKey != null){
			OrderMaster orderMaster = new OrderMaster();
			orderMaster.setSaasOrderKey(saasOrderKey);
			orderPay.setOrderMaster(orderMaster);
		}
	}
}
