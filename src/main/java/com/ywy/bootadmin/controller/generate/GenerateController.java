package com.ywy.bootadmin.controller.generate;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.DataResponse;
import com.ywy.bootadmin.dto.generate.GenerateDto;
import com.ywy.bootadmin.service.generate.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成 Controller
 *
 * @author ywy
 * @date 2020-04-13 16:19
 */
@Controller
@RequestMapping("generate")
@Slf4j
@Api(description = "代码生成接口")
public class GenerateController {
    @Autowired
    private GenerateService generateService;

    /**
     * 预览代码
     * @param tableName
     * @return
     */
    @GetMapping("/show")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:generate:list')")
    @ApiOperation("预览代码")
    public DataResponse show(String tableName) {
        log.info("GenerateController.show(), param: {}", tableName);
        BaseResult baseResult = generateService.getTableColumns(tableName);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    @PutMapping("/save")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:generate:list')")
    @ApiOperation("保存代码")
    public DataResponse save(GenerateDto generateDto) {
        log.info("GenerateController.save(), param: {}", generateDto);
        BaseResult baseResult = generateService.save(generateDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }
}
