package Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test{

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        Add add = new Add();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i <threadSize; ++ i){
            executorService.execute(()->{
                add.addCnt();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(add.getCnt());
    }
}
