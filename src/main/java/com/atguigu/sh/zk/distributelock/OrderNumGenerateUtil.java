package com.atguigu.sh.zk.distributelock;

/**
 * @auther zzyy
 * @create 2019-10-20 21:43
 */
public class OrderNumGenerateUtil
{
    private static int number = 0;

    public String getOrderNumber()
    {
        return ""+(++number);
    }
}
