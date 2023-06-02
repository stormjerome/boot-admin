package com.ywy.bootadmin.service.generate;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.dto.generate.GenerateDto;

/**
 * 代码生成 Service
 *
 * @author ywy
 * @date 2020-04-13 17:19
 */
public interface GenerateService {
    /**
     * 查询数据库表字段信息
     * @param tableName
     * @return
     */
    BaseResult getTableColumns(String tableName);

    /**
     * 保存代码
     * @param generateDto
     * @return
     */
    BaseResult save(GenerateDto generateDto);
}
