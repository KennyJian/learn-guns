package com.stylefeng.guns.modular.tempO.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Test;
import com.stylefeng.guns.modular.tempO.service.ITestService;

/**
 * 测试第一模板控制器
 *
 * @author fengshuonan
 * @Date 2018-09-09 13:23:33
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private String PREFIX = "/tempO/test/";

    @Autowired
    private ITestService testService;


    /**
     * 跳转到测试第一模板首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "test.html";
    }

    /**
     * 跳转到添加测试第一模板
     */
    @RequestMapping("/test_add")
    public String testAdd() {
        return PREFIX + "test_add.html";
    }

    /**
     * 跳转到修改测试第一模板
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
     * 获取测试第一模板列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(String condition) {
        return testService.selectList(null);
    }

    /**
     * 新增测试第一模板
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Test test) {
        testService.insert(test);
        return SUCCESS_TIP;
    }

    /**
     * 删除测试第一模板
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String testId) {
        Long idByInt= Long.parseLong(testId);
        testService.deleteById(idByInt);
        return SUCCESS_TIP;
    }

    /**
     * 修改测试第一模板
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Test test) {
        testService.updateById(test);
        return SUCCESS_TIP;
    }

    /**
     * 测试第一模板详情
     */
    @RequestMapping(value = "/detail/{testId}")
    @ResponseBody
    public Object detail(@PathVariable("testId") String testId) {
        Long idByInt= Long.parseLong(testId);
        return testService.selectById(idByInt);
    }
}
