package com.ywy.bootadmin.service.generate.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.ResponseCode;
import com.ywy.bootadmin.dao.generate.GenerateDao;
import com.ywy.bootadmin.dto.generate.GenerateDto;
import com.ywy.bootadmin.model.generate.BeanInfo;
import com.ywy.bootadmin.model.generate.TableField;
import com.ywy.bootadmin.service.generate.GenerateService;
import com.ywy.bootadmin.util.GenerateUtil;
import com.ywy.bootadmin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 Service实现
 *
 * @author ywy
 * @date 2020-04-13 17:21
 */
@Service
public class GenerateServiceImpl implements GenerateService {
    private static final Map<String, String> tableColumnMap = new HashMap();

    static {
        tableColumnMap.put("int", "Integer");
        tableColumnMap.put("tinyint", "Integer");
        tableColumnMap.put("double", "Double");
        tableColumnMap.put("float", "Float");
        tableColumnMap.put("decimal", "BigDecimal");
        tableColumnMap.put("date", "Date");
        tableColumnMap.put("timestamp", "Date");
        tableColumnMap.put("datetime", "Date");
        tableColumnMap.put("varchar", "String");
        tableColumnMap.put("text", "String");
        tableColumnMap.put("longtext", "String");
    }

    @Autowired
    private GenerateDao generateDao;

    @Override
    public BaseResult getTableColumns(String tableName) {
        List<TableField> tableFieldList = generateDao.getTableColumns(tableName);
        if (CollectionUtils.isEmpty(tableFieldList)) {
            return new BaseResult(ResponseCode.GEN_TABLE_NON_EXISTENT);
        }

        tableFieldList.forEach(tableField -> {
            tableField.setFieldName(StringUtil.lineToHump(tableField.getColumnName()));
            String filedType = tableColumnMap.get(tableField.getDataType());
            if (filedType == null) {
                filedType = "String";
            }
            tableField.setFieldType(filedType);
            if (tableField.getColumnDefault() == null) {
                tableField.setColumnDefault("");
            }
        });

        BeanInfo beanInfo = new BeanInfo();
        String beanName = StringUtil.toUpperCaseFirstChar(StringUtil.lineToHump(tableName));
        beanInfo.setBeanName(beanName);
        beanInfo.setFields(tableFieldList);

        return new BaseResult(beanInfo);
    }

    @Override
    public BaseResult save(GenerateDto generateDto) {
        GenerateUtil.genModel(generateDto);
        GenerateUtil.genDao(generateDto);
        GenerateUtil.genService(generateDto);
        GenerateUtil.genServiceImpl(generateDto);
        GenerateUtil.genController(generateDto);
        GenerateUtil.genHtml(generateDto);
        return new BaseResult();
    }
}
