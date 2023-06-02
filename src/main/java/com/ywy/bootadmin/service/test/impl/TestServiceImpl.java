package com.ywy.bootadmin.service.test.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.PageResult;
import com.ywy.bootadmin.common.rest.ResponseCode;
import com.ywy.bootadmin.dao.test.TestDao;
import com.ywy.bootadmin.model.test.Test;
import com.ywy.bootadmin.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Service实现
 * @author ywy
 * @date
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public BaseResult listByPage(Integer offset, Integer limit, Map<String, Object> params) {
        Integer count = testDao.count(params);
        List<Test> list = testDao.listByPage(offset, limit, params);
        PageResult<Test> result = new PageResult<>();
        result.setTotal(count);
        result.setDatas(list);
        return new BaseResult(result);
    }

    @Override
    public BaseResult save(Test test) {
        Integer count = testDao.save(test);
        if(count > 0) {
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.SAVE_ERROR);
    }

    @Override
    public BaseResult update(Test test) {
        Integer count = testDao.update(test);
        if(count > 0) {
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.UPDATE_ERROR);
    }

    @Override
    public BaseResult delete(Integer id) {
        Integer count = testDao.delete(id);
        if (count > 0) {
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.DELETE_ERROR);
    }

    @Override
    public Test getTestById(Integer id) {
        return testDao.getById(id);
    }

}
