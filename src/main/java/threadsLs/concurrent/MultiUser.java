package threadsLs.concurrent;

public class MultiUser {

    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " before check" );
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        Thread masterSlave = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " before check ");
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "masterSlave"
        );
        master.start();
        slave.start();
        masterSlave.start();

    }


}
