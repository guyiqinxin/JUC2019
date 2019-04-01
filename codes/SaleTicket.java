package com.atguigu.juc.study;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket
{
    private int number = 30;

    private Lock lock = new ReentrantLock();// List list = new ArrayList();

    public  void sale()
    {
        lock.lock();
        try
        {
            if(number > 0)
            {
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：三个售票员     卖出      30张票
 *	多线程编程的企业级套路+模板
 *
 *  1 线程            操作          资源类
 *  2 高内聚   低耦合
 *
 *  =====
 *  talk is cheap,show me your code
 *
 *  * synchronized  ---> Lock
 *
 */
public class SaleTicket
{
    public static void main(String[] args) throws Exception//main一切程序入口
    {
        Ticket ticket = new Ticket();

        new Thread(() -> { for (int i = 1; i <=40; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 1; i <=40; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 1; i <=40; i++) ticket.sale(); }, "C").start();


    }
}

/*
    1 extends Thread
    2 implements Runnable
    3 匿名内部类
    4 Lambda Express

    class MyThread implements Runnable
    {
        public void run()
        {
        }
    }

    Thread t1 = new Thread(new MyThread());

* */