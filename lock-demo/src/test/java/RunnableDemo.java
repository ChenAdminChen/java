import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;

public class RunnableDemo {

    //two thread(main-thread,myself-create-thread)
    //two thread executor is not order deterministic
    //
    @Test
    public void RunnableTest() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();

            System.out.println("hello: " + threadName);
        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Done!");
    }

    //TimeUnit usage
    @Test
    public void RunnableTestTime() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();

            System.out.println("hello: " + threadName);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Done!!!! ");

        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Done!");
    }

    //Executors create ExecutorService (a thread), and it is a thread pool that is one size
    // Executors are capable of running asynchronous tasks and typically manage a pool of threads
    //ExecutorsService provides two method-function (shutdown() and shutdownNow())
    // -> shutdown()  wait the thread finish , after it lift-cycle death
    // -> shutdownNow() the thread now over lift-cycle

    @Test
    public void ExecutorTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("hello  " + threadName);
            System.out.println("executor service ");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        try {
            System.out.println("over executorService lift-cycle ");
            executorService.shutdown();

            //await 5 seconds ,after interrupting all tasks
            //it is softly interrupting thread

            executorService.awaitTermination(5, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            System.out.println("task interrupted");

        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("cancel non-finished task");
            }
            executorService.shutdownNow();
            System.out.println("finished task");

        }
        System.out.println("Done!! ");
    }

    //Callable and future
    @Test
    public void CallableTest() {

        Callable<Integer> task = () -> {

            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };


        ExecutorService callable = Executors.newFixedThreadPool(1);

        Future<Integer> future = callable.submit(task);

        System.out.println("future done? " + future.isDone());

        try {

//            callable.shutdownNow();

            System.out.println("future done? " + future.isDone());

            //task must alive, this get() is block or wait until callable has been terminate
//            Integer result = future.get();

            //executor thread need 2 seconds but future.get() wait 1 second ,thus throw TimeoutException
            Integer result = future.get(1, TimeUnit.SECONDS);

            System.out.println("future result: " + result);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }


    //invokeAll
    @Test
    public void InvokeAllTest() {
        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> list = Arrays.asList(
                () -> "task 1",
                () -> "task 2",
                () -> "task 3"
        );

        try {
            executorService.invokeAll(list)
                    .stream()
                    .map(future -> {

                        try {

                            String resutl = future.get();
                            return resutl;

                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }

                    })
                    .forEach(r -> System.out.println(r));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

