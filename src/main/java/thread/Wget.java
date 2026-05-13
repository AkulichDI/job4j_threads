package thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Wget  implements Runnable{

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }


    @Override
    public void run() {

        var startAt = System.currentTimeMillis();
        var file = new File(url.substring(url.lastIndexOf('/') + 1));
        String url = this.url;

        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {


            int bytesRead;
            int downloaded = 0;
            long start = System.currentTimeMillis();


            System.out.println("Open connection: " + ((double)(System.currentTimeMillis() - startAt)/ 1000) + " seconds.");


            var dataBuffer = new byte[this.speed ];

            while ((bytesRead = input.read(dataBuffer)) != -1) {
                output.write(dataBuffer, 0, bytesRead);

                downloaded += bytesRead;

                if (downloaded >= speed) {
                    long elapsed = System.currentTimeMillis() - start;
                    System.out.println("Read "+ this.speed +  " bytes : " + (double)(System.currentTimeMillis() - start) + " millisecond.  downloaded: " + downloaded + " bytes. elapsed: " + elapsed  );
                    if (elapsed < 1000) {
                        Thread.sleep(1000 - elapsed);
                    }

                    downloaded = 0;
                    start = System.currentTimeMillis();
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        byte[] dataBuffer = new byte[speed];






    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
