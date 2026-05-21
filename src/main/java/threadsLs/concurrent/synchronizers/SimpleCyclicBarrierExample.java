package threadsLs.concurrent.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SimpleCyclicBarrierExample {



    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Runnable task = () -> {
            String name = Thread.currentThread().getName();

            try {
                System.out.println(name + " выполняет первую часть работы");
                Thread.sleep((long) (Math.random() * 3000));

                System.out.println(name + " дошел до барьера");
                barrier.await();

                System.out.println(name + " продолжил работу после барьера");

                Thread.sleep((long) (Math.random() * 3000));
                System.out.println(name + " ждем остальные потоки у барьера");
                barrier.await();
                System.out.println(name + " заканчиваем");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(name + " был прерван");
            } catch (BrokenBarrierException e) {
                System.out.println(name + " не смог пройти барьер: барьер сломан");
            }
        };

        new Thread(task, "Поток-1").start();
        new Thread(task, "Поток-2").start();
        new Thread(task, "Поток-3").start();
    }


}
