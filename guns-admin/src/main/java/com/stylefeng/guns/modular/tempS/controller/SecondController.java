package com.stylefeng.guns.modular.tempS.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Second;
import com.stylefeng.guns.modular.tempS.service.ISecondService;

/**
 * 测试第二模板控制器
 *
 * @author fengshuonan
 * @Date 2018-09-09 13:24:02
 */
@Controller
@RequestMapping("/second")
public class SecondController extends BaseController {

    private String PREFIX = "/tempS/second/";

    @Autowired
    private ISecondService secondService;


    /**
     * 跳转到测试第二模板首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "second.html";
    }

    /**
     * 跳转到添加测试第二模板
     */
    @RequestMapping("/second_add")
    public String secondAdd() {
        return PREFIX + "second_add.html";
    }

    /**
     * 跳转到修改测试第二模板
     */
    @RequestMapping("/second_update/{secondId}")
    public String secondUpdate(@PathVariable String secondId, Model model) {
        Long idByInt= Long.parseLong(secondId);
        Second second = secondService.selectById(idByInt);
        model.addAttribute("item",second);
        LogObjectHolder.me().set(second);
        return PREFIX + "second_edit.html";
    }

    /**
     * 获取测试第二模板列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return secondService.selectList(null);
    }

    /**
     * 新增测试第二模板
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Second second) {
        secondService.insert(second);
        return SUCCESS_TIP;
    }

    /**
     * 删除测试第二模板
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String secondId) {
        Long idByInt= Long.parseLong(secondId);
        secondService.deleteById(idByInt);
        return SUCCESS_TIP;
    }

    /**
     * 修改测试第二模板
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Second second) {
        secondService.updateById(second);
        return SUCCESS_TIP;
    }

    /**
     * 测试第二模板详情
     */
    @RequestMapping(value = "/detail/{secondId}")
    @ResponseBody
    public Object detail(@PathVariable("secondId") String secondId) {
        Long idByInt= Long.parseLong(secondId);
        return secondService.selectById(idByInt);
    }
}
