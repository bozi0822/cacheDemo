package com.bob.general;

import com.bob.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class TestProjDemo {
    private static final ConcurrentHashMap<String, String> cacheMap = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) {
        String proTimeout = "4";// unit: H
        long proTimeoutLong = Long.parseLong(proTimeout);
        while (true) {
            System.out.println("===========pushed error message===========");
            Scanner input = new Scanner(System.in);
            String deviceCode = input.nextLine();
            input = new Scanner(System.in);
            String lastUpdateTime = input.nextLine();

            String lastUpdateTimeCache = cacheMap.get(deviceCode);
            //当缓存里没有记录时
            if (!cacheMap.containsKey(deviceCode)){
                //将记录插入缓存，并推送预警
                cacheMap.put(deviceCode, lastUpdateTime);
                System.out.println("Cache is null, map put " + deviceCode + " => " + lastUpdateTime);
                System.out.println("Push Error Msg 1st=============");
                System.out.println("true");
            }else {//当缓存里有记录时
                //比较时间
                //时间间隔大于4H（设定处理时间）推送预警 2020-1-9 15:00:00
                Date date =new Date();
                long time = date.getTime();
                SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now = format.format(time);

                long distance = TimeUtils.getDistanceTimes(now,lastUpdateTimeCache)[1];
                System.out.println("now => " + now + "\nlastUpdateTimeCache => " + lastUpdateTimeCache + "\ndistance => " + distance);

                if (distance >= proTimeoutLong){
                    System.out.println("Push Error Msg 2ed=============");
                    System.out.println("true");
                    cacheMap.remove(deviceCode,lastUpdateTime);
                }else {
                    System.out.println("false");
                }
            }

        }
    }
}
