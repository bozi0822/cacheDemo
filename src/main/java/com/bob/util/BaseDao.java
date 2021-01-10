package com.bob.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {

    /**
     * SELECT * FROM websites
     * 查询所有记录，以List返回
     * list对象的每一个元素都是一条记录
     * 每条记录保存在Map<String, Object>里面，String类型指字段名字，Object对应字段值
     *
     * @param rs
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryList(ResultSet rs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            // 获取结果集结构（元素据）
            ResultSetMetaData rmd = rs.getMetaData();
            // 获取字段数（即每条记录有多少个字段）
            int columnCount = rmd.getColumnCount();
            while (rs.next()) {
                // 保存记录中的每个<字段名-字段值>
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; ++i) {
                    // <字段名-字段值>
                    rowData.put(rmd.getColumnName(i), rs.getObject(i));
                }
                // 获取到了一条记录，放入list
                list.add(rowData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }



}
