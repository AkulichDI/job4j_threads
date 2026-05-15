package threadsLs.concurrent;

public class Flag {

    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(
                () -> {
                    while (flag) {
                        System.out.println(Thread.currentThread().getName()+ " " + flag);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.getAllStackTraces());
                            Thread.interrupted();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        flag = false;
        thread.join();
    }


}
