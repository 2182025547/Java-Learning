package MyJava.thread;

import java.util.concurrent.*;

/**
 * 三个线程打印1-100
 *
 * @author song runqi
 * @date 2023-09-21
 */

public class ThreeThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        useExecutors();
    }

    //使用Executors线程池
    public static void useExecutors() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future submit = service.submit(new Sell("Thread 1"));
        Future submit1 = service.submit(new Sell("Thread 2"));
        Future submit2 = service.submit(new Sell("Thread 3"));
        System.out.println(submit2.get());
        System.out.println(submit.get());
        System.out.println(submit1.get());
        service.shutdown();
    }
}


class Sell implements Callable {
    private static volatile Integer tickets = 100;
    private String name;

    private static int count = 0;//记录卖出的票数


    public Sell(String name) {
        this.name = name;
    }

    @Override
    public  Object call() throws Exception {

        while (tickets > 0) {
            count++;
            System.out.println(name + "售卖了第" + (tickets--) + "张票" + ",还剩" + tickets);
//            Thread.sleep(1000);

        }

        return "线程" + name + "执行完毕" + count;
    }
}
