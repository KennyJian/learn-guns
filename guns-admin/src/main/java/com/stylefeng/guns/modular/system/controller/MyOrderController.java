package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.MyOrder;
import com.stylefeng.guns.modular.system.service.IMyOrderService;
import com.stylefeng.guns.core.idworker.Sid;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-09-07 16:23:57
 */
@Controller
@RequestMapping("/myOrder")
public class MyOrderController extends BaseController {

    private String PREFIX = "/system/myOrder/";

    @Autowired
    private IMyOrderService myOrderService;

    @Autowired
    private Sid sid;

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "myOrder.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/myOrder_add")
    public String myOrderAdd() {
        return PREFIX + "myOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/myOrder_update/{myOrderId}")
    public String myOrderUpdate(@PathVariable Integer myOrderId, Model model) {
        MyOrder myOrder = myOrderService.selectById(myOrderId);
        model.addAttribute("item",myOrder);
        LogObjectHolder.me().set(myOrder);
        return PREFIX + "myOrder_edit.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return myOrderService.selectList(null);
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MyOrder myOrder) {
        myOrder.setId(sid.nextShort());
        myOrderService.insert(myOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer myOrderId) {
        myOrderService.deleteById(myOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MyOrder myOrder) {
        myOrderService.updateById(myOrder);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{myOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("myOrderId") Integer myOrderId) {
        return myOrderService.selectById(myOrderId);
    }
}
