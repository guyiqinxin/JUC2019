package com.atguigu.juc.study;

@FunctionalInterface
interface Foo
{
    //public void sayHello();

    public int add(int x,int y);

    public default int div(int x,int y)
    {
        return x/y;
    }

    public static int mul(int x ,int y)
    {
        return x * y;
    }
}

/**
 * 1    lambda Express
 * ***  拷贝小括号，写死右箭头，落地大括号
 * 1.1 函数式接口才能用Lambda Express
 * 1.2 如何申明一个函数式接口?
 * 1.3 default支持接口内有方法的实现
 * 1.4
 */
public class LambdaExpressDemo
{
    public static void main(String[] args)
    {
        /*Foo foo = new Foo()
        {
            @Override
            public void sayHello()
            {
                System.out.println("***********hello 0925");
            }

            @Override
            public int add(int x, int y)
            {
                return 11;
            }
        };
        foo.sayHello();*/


        Foo foo = (x,y) -> {
            System.out.println("*******come in");
            return x + y;
        };

        System.out.println(foo.add(3, 5));
        System.out.println(foo.div(10, 5));
        System.out.println(Foo.mul(1,5));
    }
}
