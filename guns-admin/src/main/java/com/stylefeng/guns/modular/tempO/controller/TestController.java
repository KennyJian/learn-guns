package com.stylefeng.guns.modular.tempO.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.modular.system.model.Test;
import com.stylefeng.guns.modular.tempO.service.ITestService;

/**
 * 测试数据范围控制器
 *
 * @author fengshuonan
 * @Date 2018-09-17 17:37:56
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private String PREFIX = "/tempO/test/";

    @Autowired
    private ITestService testService;


    /**
     * 跳转到测试数据范围首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "test.html";
    }

    /**
     * 跳转到添加测试数据范围
     */
    @RequestMapping("/test_add")
    public String testAdd() {
        return PREFIX + "test_add.html";
    }

    /**
     * 跳转到修改测试数据范围
     */
    @RequestMapping("/test_update/{testId}")
    public String testUpdate(@PathVariable String testId, Model model) {
        Long idByInt= Long.parseLong(testId);
        Test test = testService.selectById(idByInt);
        model.addAttribute("item",test);
        LogObjectHolder.me().set(test);
        return PREFIX + "test_edit.html";
    }

    /**
     * 获取测试数据范围列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(    @RequestParam(required = false) String id,
    @RequestParam(required = false) String orderName,
    @RequestParam(required = false) String orderNum,
    @RequestParam(required = false) String orderTo,
    @RequestParam(required = false) String deptid){
        EntityWrapper<Test> entityWrapper=new EntityWrapper<>();
        if(ToolUtil.isNotEmpty(id)){
            entityWrapper.like("id",id);
        }
        if(ToolUtil.isNotEmpty(orderName)){
            entityWrapper.like("order_name",orderName);
        }
        if(ToolUtil.isNotEmpty(orderNum)){
            entityWrapper.like("order_num",orderNum);
        }
        if(ToolUtil.isNotEmpty(orderTo)){
            entityWrapper.like("order_to",orderTo);
        }
        if(ToolUtil.isNotEmpty(deptid)){
            entityWrapper.like("deptid",deptid);
        }
        if (!ShiroKit.isAdmin()){
            entityWrapper.in("deptid",ShiroKit.getDeptDataScope());
        }
        return testService.selectList(entityWrapper);
    }

    /**
     * 新增测试数据范围
     */
    @Permission
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Test test, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        testService.insert(test);
        return SUCCESS_TIP;
    }

    /**
     * 删除测试数据范围
     */
    @Permission
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String testId) {
        Long idByInt= Long.parseLong(testId);
        testService.deleteById(idByInt);
        return SUCCESS_TIP;
    }

    /**
     * 修改测试数据范围
     */
    @Permission
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Valid Test test, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        testService.updateById(test);
        return SUCCESS_TIP;
    }

    /**
     * 测试数据范围详情
     */
    @RequestMapping(value = "/detail/{testId}")
    @ResponseBody
    public Object detail(@PathVariable("testId") String testId) {
        Long idByInt= Long.parseLong(testId);
        return testService.selectById(idByInt);
    }
}
