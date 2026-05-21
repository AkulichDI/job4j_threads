package threadsLs.concurrent.synchronizers;

import java.util.concurrent.Phaser;

public class SimplePhaserExample {


    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        Runnable task = () -> {
            String name = Thread.currentThread().getName();

            System.out.println(name + " выполняет фазу 0");
            sleep(1000);

            System.out.println(name + " завершил фазу 0 и ждет остальных");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " выполняет фазу 1");
            sleep(1000);

            System.out.println(name + " завершил фазу 1 и ждет остальных");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " завершил всю работу");
        };

        new Thread(task, "Worker-1").start();
        new Thread(task, "Worker-2").start();
        new Thread(task, "Worker-3").start();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
