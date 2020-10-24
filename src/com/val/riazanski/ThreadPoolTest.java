package com.val.riazanski;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int wordValue = 90;
        int tr = wordValue / 3;
        Book book;
        book = new Book(wordValue);
        List<String>[] tom;
        tom = new List[3];
        tom[0] = book.subBook(0, tr, wordValue);
        tom[1] = book.subBook(tr, 2 * tr, wordValue);
        tom[2] = book.subBook(2 * tr, wordValue, wordValue);

        Runnable t1 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                for (int i = 0; i < tr; i++) {
                    System.out.println(ConsoleColors.PURPLE + threadName + tom[1].toString() + ConsoleColors.RESET);
                    tom[1].set(i, book.createUpperWord(3));
                    System.out.println(ConsoleColors.BLUE + threadName + tom[1].toString() + ConsoleColors.RESET);
                    Thread.sleep((int) (10 * Math.random()));
                }
            } catch (InterruptedException e) {
                {
                }
            }
        };
        Runnable t2 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                for (int i = 0; i < tr; i++) {
                    System.out.println(ConsoleColors.GREEN + threadName + tom[2].toString() + ConsoleColors.RESET);
                    tom[2].set(i, book.createUpperWord(3));
                    System.out.println(ConsoleColors.RED + threadName + tom[2].toString() + ConsoleColors.RESET);
                    Thread.sleep((int) (10 * Math.random()));
                }
            } catch (InterruptedException e) {
                {
                }
            }
        };
        Runnable t3 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                for (int i = 0; i < tr; i++) {
                    System.out.println(ConsoleColors.CYAN + threadName + tom[3].toString() + ConsoleColors.RESET);
                    tom[3].set(i, book.createUpperWord(3));
                    System.out.println(ConsoleColors.YELLOW + threadName + tom[3].toString() + ConsoleColors.RESET);
                    Thread.sleep((int) (10 * Math.random()));
                }
            } catch (InterruptedException e) {
                {
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(3);
        //for (Runnable task : t) service.submit(task);
        service.submit(t1);
        service.submit(t2);
        service.submit(t3);
        service.shutdown();
    }
}
