package com.ywy.bootadmin.service.test;

import com.ywy.bootadmin.model.test.Test;
import com.ywy.bootadmin.common.rest.BaseResult;
import java.util.Map;

/**
 * Service
 * @author ywy
 * @date
 */
public interface TestService {
    /**
     * 分页查询信息
     * @param offset
     * @param limit
     * @param params
     * @return
     */
    BaseResult listByPage(Integer offset, Integer limit, Map<String, Object> params);

    /**
     * 保存信息
     * @param test
     * @return
     */
    BaseResult save(Test test);

    /**
     * 修改信息
     * @param test
     * @return
     */
    BaseResult update(Test test);

    /**
     * 删除信息
     * @param id
     * @return
     */
    BaseResult delete(Integer id);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    Test getTestById(Integer id);

}
