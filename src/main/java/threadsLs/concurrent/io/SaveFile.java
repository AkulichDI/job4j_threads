package threadsLs.concurrent.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class SaveFile {

    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try(OutputStream o = new FileOutputStream(file)){
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
