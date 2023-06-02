package com.ywy.bootadmin.util;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Map工具类
 *
 * @author ywy
 * @date 2020-04-17 11:11
 */
public class MapUtil {
    /**
     * 实体类转换为map，忽略父类
     * @param obj
     * @return
     */
    public static HashMap<String, Object> convertToMap(Object obj) {
        HashMap<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            try {
                Object o = fields[i].get(obj);
                if (o != null) {
                    map.put(varName, o.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            fields[i].setAccessible(accessFlag);
        }

        return map;
    }
}
