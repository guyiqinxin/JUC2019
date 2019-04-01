package com.atguigu.juc.study;

import com.atguigu.juc.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**u
 * @auther zzyy
 * @create 2019-01-01 21:15
 */
public class CountDownLatchDemo
{
    public static void main(String[] args) throws Exception
    {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *************一统华夏");

        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }

    public static void closeDoor() throws InterruptedException
    {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 同学离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *************88班长关门走人");
    }
}
