package concurrent;

public class ThreadStop {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++ + "  " + Thread.currentThread().isInterrupted());
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        System.out.println("Статус нити:" +  thread.isInterrupted());
        thread.interrupt();

        System.out.println(thread.isInterrupted());
    }
}
