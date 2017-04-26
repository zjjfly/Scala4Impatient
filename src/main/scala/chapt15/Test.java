package chapt15;

import java.io.IOException;

/**
 * Created by zjjfly on 16/6/28.
 */
public class Test {
    public static void main(String[] args) {
        Book book = new Book();
        try {
            String aafaf = book.read("aafaf");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        String process = book.process("rh", "ada");
        System.out.println(process);
        System.out.println(book.isBorrowed());
        Exercise exercise = new Exercise();
        int sum = exercise.sum(1, 3, 4);
        System.out.println(sum);
    }
}
