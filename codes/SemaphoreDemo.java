package com.atguigu.juc.study;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2019-01-02 22:11
 */
public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位

        for (int i = 1; i <=6; i++)
        {
            new Thread(() -> {
                try
                {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 占到了车位");
                    //暂停一会儿线程
                    try { TimeUnit.SECONDS.sleep( 3 ); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"\t 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }

}
