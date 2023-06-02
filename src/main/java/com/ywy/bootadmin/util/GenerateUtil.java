package com.ywy.bootadmin.util;

import com.ywy.bootadmin.dto.generate.GenerateDto;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 代码生成工具
 *
 * @author: ywy
 * @date: 2020-04-13 23:43
 */
@Slf4j
public class GenerateUtil {
    /**
     * 生成Model
     * @param generateDto
     */
    public static void genModel(GenerateDto generateDto) {
        // 获取参数
        String path = generateDto.getPath();
        String beanPackageName = generateDto.getBeanPackageName();
        String beanName = generateDto.getBeanName();
        List<String> fieldNames = generateDto.getFieldName();
        List<String> fieldTypes = generateDto.getFieldType();

        // 读取model模板文件
        String templateText = getTemplateFile("model.tmpl");
        // 替换占位符
        templateText = templateText.replace("{beanPackageName}", beanPackageName)
                .replace("{beanName}", beanName);

        // 生成导入包
        String imports = "";
        if (fieldTypes.contains(BigDecimal.class.getSimpleName())) {
            imports += "import " + BigDecimal.class.getName() + ";\n";
        }
        if (fieldTypes.contains(Date.class.getSimpleName())) {
            imports += "import " + Date.class.getName() + ";";
        }
        templateText = templateText.replace("{import}", imports);

        // 生成字段
        String fields = getFields(fieldNames, fieldTypes);
        templateText = templateText.replace("{filelds}", fields);

        // 生成getter、setter方法，可以直接在模板中使用lombok的@Data注解
//        String getterAndSetter = getterAndSetter(fieldNames, fieldTypes);
//        templateText = templateText.replace("{getset}", getterAndSetter);

        // 保存Model文件
        FileUtil.stringToFile(templateText, path + File.separator + getPackagePath(beanPackageName) + beanName + ".java");
        log.info("生成java model：{}", beanName);
    }

    /**
     * 生成Dao
     * @param generateDto
     */
    public static void genDao(GenerateDto generateDto) {
        // 获取参数
        String path = generateDto.getPath();
        String tableName = generateDto.getTableName();
        String beanName = generateDto.getBeanName();
        String daoPackageName = generateDto.getDaoPackageName();
        String daoName = generateDto.getDaoName();
        List<String> fieldNames = generateDto.getFieldName();
        List<String> columnNames = generateDto.getColumnName();

        // 读取dao模板文件
        String templateText = getTemplateFile("dao.tmpl");
        // 替换占位符
        templateText = templateText.replace("{daoPackageName}", daoPackageName)
                .replace("{beanPackageName}", generateDto.getBeanPackageName())
                .replace("{daoName}", daoName)
                .replace("{tableName}", tableName)
                .replace("{beanName}", beanName)
                .replace("{beanParamName}", StringUtil.toLowerCaseFirstChar(beanName));

        // 获取insert语句插入参数
        String insertColumns = getInsertColumns(columnNames);
        templateText = templateText.replace("{insertColumns}", insertColumns);

        // 获取insert语句插入值
        String insertValues = getInsertValues(columnNames, fieldNames);
        templateText = templateText.replace("{insertValues}", insertValues);

        // 保存Dao文件
        FileUtil.stringToFile(templateText, path + File.separator + getPackagePath(daoPackageName) + daoName + ".java");
        log.info("生成java dao：{}", beanName);

        // 读取mapper模板文件
        templateText = getTemplateFile("mapper.tmpl");
        // 替换占位符
        templateText = templateText.replace("{daoPackageName}", daoPackageName)
                .replace("{daoName}", daoName)
                .replace("{tableName}", tableName)
                .replace("{beanName}", beanName);

        // 获取update sets语句
        String updateSets = getUpdateSets(columnNames, fieldNames);
        templateText = templateText.replace("{updateSets}", updateSets);

        // 获取where语句
        String where = getWhere(columnNames, fieldNames);
        templateText = templateText.replace("{where}", where);

        // 保存Mapper XML文件
        FileUtil.stringToFile(templateText, path + File.separator + beanName + "Mapper.xml");
        log.info("生成mapper xml：{}", beanName);
    }

    /**
     * 生成Service
     * @param generateDto
     */
    public static void genService(GenerateDto generateDto) {
        // 获取参数
        String path = generateDto.getPath();
        String beanName = generateDto.getBeanName();
        String daoName = generateDto.getDaoName();
        String servicePackageName = generateDto.getServicePackageName();
        String serviceName = generateDto.getServiceName();

        // 读取service模板文件
        String templateText = getTemplateFile("service.tmpl");
        // 替换占位符
        templateText = templateText.replace("{daoPackageName}", generateDto.getDaoPackageName())
                .replace("{beanPackageName}", generateDto.getBeanPackageName())
                .replace("{daoName}", daoName)
                .replace("{daoParamName}", StringUtil.toLowerCaseFirstChar(daoName))
                .replace("{beanName}", beanName)
                .replace("{beanParamName}", StringUtil.toLowerCaseFirstChar(beanName))
                .replace("{servicePackageName}", servicePackageName)
                .replace("{serviceName}", serviceName)
                .replace("{groupPackageName}", generateDto.getGroupPackageName());

        // 保存Service文件
        FileUtil.stringToFile(templateText, path + File.separator + getPackagePath(servicePackageName)
                + serviceName + ".java");
        log.info("生成java service：{}", beanName);
    }

    /**
     * 生成ServiceImpl
     * @param generateDto
     */
    public static void genServiceImpl(GenerateDto generateDto) {
        // 获取参数
        String path = generateDto.getPath();
        String beanName = generateDto.getBeanName();
        String daoName = generateDto.getDaoName();
        String serviceImplPackageName = generateDto.getServiceImplPackageName();
        String serviceImplName = generateDto.getServiceImplName();

        // 读取serviceImpl模板文件
        String templateText = getTemplateFile("serviceImpl.tmpl");
        // 替换占位符
        templateText = templateText.replace("{daoPackageName}", generateDto.getDaoPackageName())
                .replace("{beanPackageName}", generateDto.getBeanPackageName())
                .replace("{daoName}", daoName)
                .replace("{daoParamName}", StringUtil.toLowerCaseFirstChar(daoName))
                .replace("{beanName}", beanName)
                .replace("{beanParamName}", StringUtil.toLowerCaseFirstChar(beanName))
                .replace("{serviceImplPackageName}", serviceImplPackageName)
                .replace("{serviceImplName}", serviceImplName)
                .replace("{servicePackageName}", generateDto.getServicePackageName())
                .replace("{serviceName}", generateDto.getServiceName())
                .replace("{groupPackageName}", generateDto.getGroupPackageName());

        // 保存ServiceImpl文件
        FileUtil.stringToFile(templateText, path + File.separator + getPackagePath(serviceImplPackageName)
                + serviceImplName + ".java");
        log.info("生成java serviceImpl：{}", beanName);
    }

    /**
     * 生成Controller
     * @param generateDto
     */
    public static void genController(GenerateDto generateDto) {
        // 获取参数
        String path = generateDto.getPath();
        String beanName = generateDto.getBeanName();
        String daoName = generateDto.getDaoName();
        String serviceName = generateDto.getServiceName();
        String controllerPackageName = generateDto.getControllerPackageName();
        String controllerName = generateDto.getControllerName();

        // 读取controller模板文件
        String templateText = getTemplateFile("controller.tmpl");
        // 替换占位符
        templateText = templateText.replace("{daoPackageName}", generateDto.getDaoPackageName())
                .replace("{beanPackageName}", generateDto.getBeanPackageName())
                .replace("{daoName}", daoName)
                .replace("{daoParamName}", StringUtil.toLowerCaseFirstChar(daoName))
                .replace("{beanName}", beanName)
                .replace("{beanParamName}", StringUtil.toLowerCaseFirstChar(beanName))
                .replace("{controllerPackageName}", controllerPackageName)
                .replace("{controllerName}", controllerName)
                .replace("{groupPackageName}", generateDto.getGroupPackageName())
                .replace("{serviceParamName}", StringUtil.toLowerCaseFirstChar(serviceName))
                .replace("{servicePackageName}", generateDto.getServicePackageName())
                .replace("{serviceName}", serviceName);

        // 保存Controller文件
        FileUtil.stringToFile(templateText, path + File.separator + getPackagePath(controllerPackageName)
                + controllerName + ".java");
        log.info("生成java controller：{}", beanName);
    }

    /**
     * 生成html
     * @param generateDto
     */
    public static void genHtml(GenerateDto generateDto) {
        // 获取参数
        String path = generateDto.getPath();
        String beanName = generateDto.getBeanName();
        String beanParamName = StringUtil.toLowerCaseFirstChar(beanName);
        List<String> beanFieldNames = generateDto.getFieldName();
        List<String> columnComments = generateDto.getColumnComment();

        // 读取html list模板文件
        String templateText = getTemplateFile("htmlList.tmpl");
        // 替换占位符
        templateText = templateText.replace("{beanParamName}", beanParamName)
                .replace("{beanName}", beanName)
                .replace("{columnsDatas}", getHtmlColumnsDatas(beanFieldNames));

        // 保存html list文件
        FileUtil.stringToFile(templateText, path + File.separator + beanParamName + "-list.html");
        log.info("生成查询页面：{}", beanName);

        // 读取html add模板文件
        templateText = getTemplateFile("htmlAdd.tmpl");
        // 替换占位符
        templateText = templateText.replace("{beanParamName}", beanParamName)
                .replace("{addDivs}", addOrEditDivs(beanParamName, beanFieldNames, columnComments, false));

        // 保存html add文件
        FileUtil.stringToFile(templateText, path + File.separator + beanParamName + "-add.html");
        log.info("生成添加页面：{}", beanName);

        // 读取html edit模板文件
		templateText = getTemplateFile("htmlEdit.tmpl");
        // 替换占位符
		templateText = templateText.replace("{beanParamName}", beanParamName)
                .replace("{editDivs}", addOrEditDivs(beanParamName, beanFieldNames, columnComments, true));

        // 保存html edit文件
		FileUtil.stringToFile(templateText, path + File.separator + beanParamName + "-edit.html");
		log.info("生成修改页面：{}", beanName);
    }

    /**
     * 读取模板文件
     * @param fileName
     * @return
     */
    private static String getTemplateFile(String fileName) {
        return FileUtil.fileToString(GenerateUtil.class.getClassLoader().getResourceAsStream("generate/" + fileName));
    }

    /**
     * 生成字段
     * @param fieldNames
     * @param fieldTypes
     * @return
     */
    private static String getFields(List<String> fieldNames, List<String> fieldTypes) {
        StringBuffer sb = new StringBuffer();
        int size = fieldNames.size();
        for (int i = 0; i < size; i++) {
            String fieldName = fieldNames.get(i);
            // 过滤掉id、createTime、updateTime字段
            if ("id".equals(fieldName) || "createTime".equals(fieldName) || "updateTime".equals(fieldName)) {
                continue;
            }

            String fieldType = fieldTypes.get(i);
            // 生成字段
            sb.append("\tprivate ").append(fieldType).append(" ").append(fieldName);

            sb.append(";\n\n");
        }

        // 去除最后的"\n\n"
        sb = sb.delete(sb.lastIndexOf("\n\n"), sb.length());
        return sb.toString();
    }

    /**
     * 生成getter和setter方法
     * @param fieldNames
     * @param fieldTypes
     * @return
     */
    private static String getterAndSetter(List<String> fieldNames, List<String> fieldTypes) {
        StringBuffer sb = new StringBuffer();
        int size = fieldNames.size();
        for (int i = 0; i < size; i++) {
            String fieldName = fieldNames.get(i);
            // 过滤掉id、createTime、updateTime字段
            if ("id".equals(fieldName) || "createTime".equals(fieldName) || "updateTime".equals(fieldName)) {
                continue;
            }

            String fieldType = fieldTypes.get(i);
            String name = StringUtil.toUpperCaseFirstChar(fieldName);
            sb.append("\tpublic ").append(fieldType).append(" get").append(name).append("() {\n");
            sb.append("\t\treturn ").append(fieldName).append(";\n");
            sb.append("\t}\n\n");
            sb.append("\tpublic void set").append(name)
                    .append("(").append(fieldType).append(" ").append(fieldName).append(") {\n");
            sb.append("\t\tthis.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            sb.append("\t}\n\n");
        }

        // 去除最后的"\n\n"
        sb = sb.delete(sb.lastIndexOf("\n\n"), sb.length());
        return sb.toString();
    }

    /**
     * 根据包名获取包路径
     * @param packageName
     * @return
     */
    private static String getPackagePath(String packageName) {
        String packagePath = packageName.replace(".", "/");
        if (!packagePath.endsWith("/")) {
            packagePath = packagePath + "/";
        }

        return packagePath;
    }

    /**
     * 获取insert语句插入参数
     * @param columnNames
     * @return
     */
    private static String getInsertColumns(List<String> columnNames) {
        StringBuffer sb = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            // 过滤掉id、createTime、updateTime字段
            if ("id".equals(column) || "create_time".equals(column) || "update_time".equals(column)) {
                continue;
            }
            sb.append(column).append(", ");
        }

        // 去除最后的", "
        sb = sb.delete(sb.lastIndexOf(", "), sb.length());
        return sb.toString();
    }

    /**
     * 获取insert语句插入值
     * @param columnNames
     * @param fieldNames
     * @return
     */
    private static String getInsertValues(List<String> columnNames, List<String> fieldNames) {
        StringBuffer sb = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            // 过滤掉id、createTime、updateTime字段
            if ("id".equals(column) || "create_time".equals(column) || "update_time".equals(column)) {
                continue;
            }
            sb.append("#{").append(fieldNames.get(i)).append("}, ");
        }

        // 去除最后的", "
        sb = sb.delete(sb.lastIndexOf(", "), sb.length());

        return sb.toString();
    }

    /**
     * 获取where语句
     * @param columnNames
     * @param fieldNames
     * @return
     */
    private static String getWhere(List<String> columnNames, List<String> fieldNames) {
        StringBuffer buffer = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String fieldName = fieldNames.get(i);
            buffer.append("\t\t\t\t<if test=\"params." + fieldName + " != null and params." + fieldName + " != ''\">\n");
            buffer.append("\t\t\t\t\tand " + columnNames.get(i)).append(" = ").append("#{params.")
                    .append(fieldName).append("} \n");
            buffer.append("\t\t\t\t</if>\n");
        }

        return buffer.toString();
    }

    /**
     * 获取update sets语句
     * @param columnNames
     * @param fieldNames
     * @return
     */
    private static String getUpdateSets(List<String> columnNames, List<String> fieldNames) {
        StringBuffer sb = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String fieldName = fieldNames.get(i);
            if (!"id".equals(fieldName)) {
                sb.append("\t\t\t<if test=\"" + fieldName + " != null\">\n");
                sb.append("\t\t\t\t" + columnNames.get(i)).append(" = ").append("#{").
                        append(fieldName).append("}, \n");
                sb.append("\t\t\t</if>\n");
            }
        }

        return sb.toString();
    }

    /**
     * 获取页面字段数据
     * @param fieldNames
     * @return
     */
    private static String getHtmlColumnsDatas(List<String> fieldNames) {
        StringBuilder sb = new StringBuilder();
        fieldNames.forEach(fieldName -> {
            sb.append("\t\t\t\t\t\t{field: '" + fieldName + "', title: '" + fieldName + "'},\n");
        });

        return sb.toString();
    }

    /**
     * 生成html div
     * @param beanParamName
     * @param fieldNames
     * @param isEdit 是否是编辑页面
     * @return
     */
    private static String addOrEditDivs(String beanParamName, List<String> fieldNames, List<String> columnComment, Boolean isEdit) {
        int size = fieldNames.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String fieldName = fieldNames.get(i);
            // 过滤掉id、createTime、updateTime字段
            if (!"id".equals(fieldName) && !"createTime".equals(fieldName) && !"updateTime".equals(fieldName)) {
                sb.append("\t\t\t\t<div class=\"layui-form-item\">\n");
                sb.append("\t\t\t\t\t<label for=\"" + fieldName + "\" class=\"layui-form-label\">\n");
                sb.append("\t\t\t\t\t\t<span class=\"x-red\">*</span>" + fieldName + "\n");
                sb.append("\t\t\t\t\t</label>\n");
                sb.append("\t\t\t\t\t<div class=\"layui-input-inline\">\n");
                sb.append("\t\t\t\t\t\t<input type=\"text\" id=\"" + fieldName + "\" name=\"" + fieldName + "\"");
                if (isEdit) {
                    sb.append(" th:value=\"${" + beanParamName + "." + fieldName + "}\"");
                }
                sb.append(" lay-verify=\"required\" class=\"layui-input\">\n");
                sb.append("\t\t\t\t\t</div>\n");
                sb.append("\t\t\t\t\t<div class=\"layui-form-mid layui-word-aux\">\n");
                sb.append("\t\t\t\t\t\t<span class=\"x-red\">*</span>" + columnComment.get(i) +"\n");
                sb.append("\t\t\t\t\t</div>\n");
                sb.append("\t\t\t\t</div>\n");
            }
        }
        return sb.toString();
    }
}
