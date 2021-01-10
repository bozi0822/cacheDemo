package com.bob;


import com.bob.util.BaseDao;
import com.bob.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
    private static PreparedStatement ps=null;

    public static void main(String[] args) {
        BaseDao _dao = new BaseDao();
//        while (true) {
//            Scanner input = new Scanner(System.in);
//            String key1 = input.nextLine();
//            String value1Cache = map.get(key1);
//            if (key1 != null && value1Cache == null) {
//                input = new Scanner(System.in);
//                String value1 = input.nextLine();
//                map.put(key1, value1);
//                System.out.println("Cache is null, map put " + key1 + " => " + value1);
//            } else {
//                System.out.println("key1 => " + key1);
//                System.out.println("value1 => " + value1Cache);
//            }
//        }

//        long[] distanceTimes = TimeUtils.getDistanceTimes("2020-11-20 09:00:00", "2020-11-20 13:00:00");
//        System.out.println(distanceTimes[1]);

//        Date date =new Date();
//        long time = date.getTime();
//        SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String now = format.format(time);
//
//        long distance = TimeUtils.getDistanceTimes("2021-1-9 16:00:00", now)[1];
//        System.out.println(distance);

        Connection connection = JDBCUtil.getConnection();
        if (connection == null){
            return;
        }

        try {
            String sql="select * from sys_dept sd";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Map<String, Object>> queryList = _dao.queryList(rs);
            System.out.println(queryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(null,ps,connection);
        }


    }
}
