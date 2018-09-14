package com.stylefeng.guns.modular.Vali.controller;

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
import com.stylefeng.guns.modular.system.model.Validator;
import com.stylefeng.guns.modular.Vali.service.IValidatorService;

/**
 * 测试验证控制器
 *
 * @author fengshuonan
 * @Date 2018-09-14 10:15:13
 */
@Controller
@RequestMapping("/validator")
public class ValidatorController extends BaseController {

    private String PREFIX = "/Vali/validator/";

    @Autowired
    private IValidatorService validatorService;


    /**
     * 跳转到测试验证首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "validator.html";
    }

    /**
     * 跳转到添加测试验证
     */
    @RequestMapping("/validator_add")
    public String validatorAdd() {
        return PREFIX + "validator_add.html";
    }

    /**
     * 跳转到修改测试验证
     */
    @RequestMapping("/validator_update/{validatorId}")
    public String validatorUpdate(@PathVariable String validatorId, Model model) {
        Long idByInt= Long.parseLong(validatorId);
        Validator validator = validatorService.selectById(idByInt);
        model.addAttribute("item",validator);
        LogObjectHolder.me().set(validator);
        return PREFIX + "validator_edit.html";
    }

    /**
     * 获取测试验证列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return validatorService.selectList(null);
    }

    /**
     * 新增测试验证
     */
    @Permission
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Validator validator, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        validatorService.insert(validator);
        return SUCCESS_TIP;
    }

    /**
     * 删除测试验证
     */
    @Permission
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String validatorId) {
        Long idByInt= Long.parseLong(validatorId);
        validatorService.deleteById(idByInt);
        return SUCCESS_TIP;
    }

    /**
     * 修改测试验证
     */
    @Permission
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Valid Validator validator, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        validatorService.updateById(validator);
        return SUCCESS_TIP;
    }

    /**
     * 测试验证详情
     */
    @RequestMapping(value = "/detail/{validatorId}")
    @ResponseBody
    public Object detail(@PathVariable("validatorId") String validatorId) {
        Long idByInt= Long.parseLong(validatorId);
        return validatorService.selectById(idByInt);
    }
}
