package threadsLs.concurrent.synchronizers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ApiClientExample {

    private final Semaphore limit = new Semaphore(3, true);

    public void sendRequest(int requestId) {
        boolean acquired = false;

        try {
            acquired = limit.tryAcquire(1, TimeUnit.SECONDS);

            if (!acquired) {
                System.out.println("Запрос " + requestId + " отклонен: сервис перегружен");
                return;
            }

            try {
                System.out.println("Запрос " + requestId + " отправлен");
                Thread.sleep(1500);
                System.out.println("Запрос " + requestId + " завершен");
            } finally {
                limit.release();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Запрос " + requestId + " прерван");
        }
    }

    public static void main(String[] args) {
        ApiClientExample client = new ApiClientExample();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            int requestId = i;
            pool.submit(() -> client.sendRequest(requestId));
        }

        pool.shutdown();
    }


}
