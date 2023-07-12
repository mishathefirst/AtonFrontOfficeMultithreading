package ru.aton.multithreading;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phrase {

    private String phrase;
    private File file;
    private List<String> fileStrings = new ArrayList<>();
    private int i = 0;

    public void readFile(String fileName) {
        file = new File(fileName);
        try {
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNext()) {
                fileStrings.add(fileScanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readPhrase(String threadName) {
        try {
            while (i < fileStrings.size()) {
                if (fileStrings.get(i).startsWith(threadName)) {
                    System.out.println(fileStrings.get(i));
                    //Подтверждение печати строки различными потоками:
                    //System.out.println(Thread.currentThread().getName());
                    i++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
