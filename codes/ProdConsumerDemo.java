package com.atguigu.juc.study;


class ShareData
{
}

/**
 * 现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，交替，来10轮。
    上
 */
public class ProdConsumerDemo
{
    public static void main(String[] args)
    {
        ShareData sd = new ShareData();

    }
}
