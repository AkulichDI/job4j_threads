package threadsLs.concurrent.synchronizers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class LimitedServiceExample {

    private static final Semaphore SEMAPHORE = new Semaphore(2, true);

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            int userId = i;

            pool.submit(() -> {
                try {
                    SEMAPHORE.acquire();

                    try {
                        System.out.println("Пользователь " + userId + " вошел в сервис");
                        Thread.sleep(2000);
                        System.out.println("Пользователь " + userId + " вышел из сервиса");
                    } finally {
                        SEMAPHORE.release();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Пользователь " + userId + " был прерван");
                }
            });
        }

        pool.shutdown();
    }


}
