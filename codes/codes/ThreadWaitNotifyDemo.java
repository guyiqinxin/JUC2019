package com.atguigu.juc.study;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment()throws Exception
    {
        lock.lock();
        try
        {
            //1 判断
            while(number != 0)
            {

                condition.await();//this.wait();fd
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3 通知
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement()throws Exception
    {
        lock.lock();
        try
        {
            //1 判断
            while(number == 0)
            {

                condition.await();//this.wait();fd
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3 通知
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /*public synchronized void increment()throws Exception
    {
        //1 判断
        while(number != 0)
        {
            this.wait();// A C
        }
        //2 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3 通知
        this.notifyAll();
    }

    public synchronized void decrement()throws Exception
    {
        //1 判断
        while(number == 0)
        {
            this.wait();
        }
        //2 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3 通知
        this.notifyAll();
    }*/
}


/**
 * @auther zzyy
 * @create 2019-02-19 8:44
 * 题目：现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 *
 * 1 高内聚低耦合前提下，线程   操作      资源类
 * 2 判断+干活+通知
 * 3 避免虚假唤醒，线程判断用while
 */
public class ThreadWaitNotifyDemo
{
    public static void main(String[] args)
    {
        ShareResource sr = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <=10 ; i++)
            {
                try {
                    sr.increment();
                    //暂停一会儿线程
                    try { Thread.sleep( 200 ); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <=10 ; i++)
            {
                try {
                    sr.decrement();
                    try { Thread.sleep( 300 ); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++)
            {
                try {
                    sr.increment();
                    try { Thread.sleep( 400 ); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 1; i <=10 ; i++)
            {
                try {
                    sr.decrement();
                    try { Thread.sleep( 500 ); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}
