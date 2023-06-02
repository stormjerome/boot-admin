package com.ywy.bootadmin.controller.test;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.DataResponse;
import com.ywy.bootadmin.common.rest.PageInfo;
import com.ywy.bootadmin.model.test.Test;
import com.ywy.bootadmin.service.test.TestService;
import com.ywy.bootadmin.util.MapUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

/**
 * Controller
 * @author ywy
 * @date
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    /**
     * 列表
     * @param pageInfo
     * @param test
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('test:query')")
    @ApiOperation(value = "分页查询列表")
    public DataResponse list(PageInfo pageInfo, Test test) {
        log.info("TestController.list(), param: {}, {}", pageInfo, test);

        HashMap<String, Object> paramsMap = MapUtil.convertToMap(test);
        BaseResult baseResult = testService.listByPage(pageInfo.getOffset(), pageInfo.getLimit(), paramsMap);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('test:add')")
    @ApiOperation("跳转到添加页面")
    public String toAdd() {
        return "/test/test-add";
    }

    /**
     * 添加信息
     * @param test
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('test:add')")
    @ApiOperation(value = "添加信息")
    public DataResponse add(Test test) {
        log.info("TestController.add(), param: {}", test);

        BaseResult baseResult = testService.save(test);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到修改页面
     * @return
     */
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('test:edit')")
    @ApiOperation("跳转到修改页面")
    public String toEdit(Model model, Integer id) {
        Test test = testService.getTestById(id);
        model.addAttribute("test", test);
        return "/test/test-edit";
    }

    /**
     * 修改信息
     * @param test
     * @return
     */
    @PutMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('test:edit')")
    @ApiOperation("修改信息")
    public DataResponse update(Test test) {
        log.info("TestController.edit(), param: {}", test);

        BaseResult baseResult = testService.update(test);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('test:del')")
    @ApiOperation("删除信息")
    public DataResponse delete(Integer id) {
        log.info("TestController.delete(), param: {}", id);

        BaseResult baseResult = testService.delete(id);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }
}
