package com.stone;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeTest
 *
 * @author Young
 * @date 2016/2/16 0016
 */
public class TimeTest {
    public static void main(String[] args) {
        String trade_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//交易日期
        System.out.println(trade_date);
    }
}
