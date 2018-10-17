package org.saas.qa.controller;

import org.saas.qa.domain.AbnormalOrder;
import org.saas.qa.service.QaService;
import org.saas.qa.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AbnormalOrderController {
    /**
     * 自动注入qaService
     */
    @Autowired
    @Qualifier("qaService")
    private QaService qaService;

    @RequestMapping(value="/abnormalOrder/selectAbnormalOrderDetail")
    public String selectAbnormalOrder(Model model){
        List<AbnormalOrder> abnormalOrders = qaService.findAllAbnormalOrder();
        /*
        for(AbnormalOrder ab : abnormalOrders){
             System.out.println(ab.getDescription());
        }*/
       
        model.addAttribute("abnormalOrders",abnormalOrders);
        return "/abnormalorder/abnormalorder";
    }

    @RequestMapping(value="/abnormalorder/repairOrder")
    public ModelAndView repairOrder(ModelAndView mv){
        mv.setViewName("abnormalorder/showRepairOrders");
        return mv;
    }

    //生成sql
    @RequestMapping(value="/abnormalorder/produceSql")
    public ModelAndView produceSql(String orderKey,
        ModelAndView mv){
        //List<String> sql = new ArrayList<>();
        String sql = "delete from tbl_saas_order_pay where orderKey in(" + orderKey + ");";
        mv.addObject("sql",sql);
        mv.setViewName("abnormalorder/showRepairOrders");
        return mv;
    }
}
