package threadsLs.concurrent.synchronizers;

import java.util.concurrent.Semaphore;

public class PrinterExample {

    private static final Semaphore PRINTER = new Semaphore(3,  true);

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();

            try {
                PRINTER.acquire();

                try {
                    System.out.println(threadName + " начал печать");
                    Thread.sleep(1000);
                    System.out.println(threadName + " закончил печать");
                } finally {
                    PRINTER.release();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(threadName + " был прерван");
            }
        };

        new Thread(task, "Поток-1").start();
        new Thread(task, "Поток-2").start();
        new Thread(task, "Поток-3").start();
    }



}
