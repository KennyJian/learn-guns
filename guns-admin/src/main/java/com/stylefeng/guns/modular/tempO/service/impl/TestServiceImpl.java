package com.stylefeng.guns.modular.tempO.service.impl;

import com.stylefeng.guns.modular.system.model.Test;
import com.stylefeng.guns.modular.system.dao.TestMapper;
import com.stylefeng.guns.modular.tempO.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kenny
 * @since 2018-09-09
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
