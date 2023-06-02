package com.ywy.bootadmin.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Druid Controller
 *
 * @author ywy
 * @date 2020-04-11 11:30
 */
@RestController
@ApiIgnore
public class DruidStatController {
    /**
     * 获取所有数据源的监控数据
     * @return
     */
    @GetMapping("/druid/stat")
    public Object druidStat() {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
