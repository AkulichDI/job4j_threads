package threadsLs.concurrent;

public class Wget {

    public static void main(String[] args) {


        Thread t = new Thread( () ->
        {
            for (int i = 0; i <= 100; i++) {

                try {
                    System.out.print("\rLoading : " + i  + "%");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        t.start();
    }



}
