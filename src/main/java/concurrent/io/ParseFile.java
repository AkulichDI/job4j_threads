package concurrent.io;

import java.io.*;
import java.util.function.Predicate;


/**
 * V - Избавиться от get set за счет передачи File в конструктор.
 * V - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * - Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 **/

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }


    public String getContent() {
        return content(ch -> true);
    }

    public String getContentWithoutUnicode() {
        return content(ch -> ch < 0x80);
    }

    public synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();

        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                char ch = (char) data;
                if (filter.test(ch)) {
                    output.append(ch);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return output.toString();
    }





}
