package com.bob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计算从上一级审核时间到下一级审核时间的小时数
 */
public class RequiredTimeHours {

    public static int getHours(Date date) {
        try {
            //将传入的日期类型转化为固定的格式，以便获取参数时间的毫秒数
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = sdf.format(date);
            //获取参数毫秒数
            long time = sdf.parse(format).getTime();
            //在中间加个循环，以加大时间差
            for(int i=0;i<50;i++){
                System.out.print(i+"-");
            }
            //获取系统当前时间的毫秒数
            long l = System.currentTimeMillis();
            //两者相减，获得时间差的毫秒数
            long mills = l - time;
            System.out.println(l);
            System.out.println(time);
            System.out.println(mills);//如果这里的值不为零 证明时间差计算成功
            //将时间差的毫秒数转化成整个的小时数
            int requiredHours = (int) (mills / 1000 / 3600);
            //将所需时间返回
            return requiredHours;
        } catch (Exception e) {
            //返回一个0，代表执行出现异常
            return 0;
        }
    }
    public static void main(String[] args) {
        System.out.println(getHours(new Date()));
    }
}
