package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.dictmap.OrderDict;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-09-08 19:36:50
 */
@Controller
@RequestMapping("/myOrder")
public class MyOrderController extends BaseController {

    private String PREFIX = "/system/myOrder/";

    @Autowired
    private IMyOrderService myOrderService;


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
    public String myOrderUpdate(@PathVariable String myOrderId, Model model) {
        Long idByInt= Long.parseLong(myOrderId);
        MyOrder myOrder = myOrderService.selectById(idByInt);
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
        if(ToolUtil.isNotEmpty(condition)){
            EntityWrapper<MyOrder> myOrderEntityWrapper=new EntityWrapper<>();
            Wrapper<MyOrder> gooods=myOrderEntityWrapper.like("goods","%"+condition+"%");
            return myOrderService.selectList(gooods);
        }
        return myOrderService.selectList(null);
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加订单",key = "user,place",dict = OrderDict.class)
    @ResponseBody
    @ApiOperation(value = "添加订单")
    public Object add(MyOrder myOrder) {
        myOrderService.insert(myOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除订单",key = "myOrderId",dict = OrderDict.class)
    @ResponseBody
    public Object delete(@RequestParam String myOrderId) {
        Long idByInt= Long.parseLong(myOrderId);
        myOrderService.deleteById(idByInt);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改订单",key = "user,place",dict = OrderDict.class)
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
    public Object detail(@PathVariable("myOrderId") String myOrderId) {
        Long idByInt= Long.parseLong(myOrderId);
        return myOrderService.selectById(idByInt);
    }
}
