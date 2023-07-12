package ru.aton.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Программа создаёт 6 потоков со своими именами, в качестве потока запускается Actor, реализующий Runnable;
Actor имеет основные поля и метод run() для запуска потока;
Чтение фраз осуществляется методом readPhrase() класса Phrase, причём после чтения файла методом readFile();
Для предотвращения гонки и печати фраз в случайном порядке использован модификатор synchronized;
Поток, зашедший в метод чтения фраз, проверяет, является ли фраза его;
Если является, то печатает её и оповещает остальные потоки методом notifyAll() об освобождении метода;
Если не является, то встаёт в ожидание до освобождения ресурса потоком, который прочитает фразу.
 */

public class Main {
    public static void main(String[] args) {

        String fileName = "./src/ru/aton/multithreading/PhrasesFile.txt";
        Phrase phrase = new Phrase();
        phrase.readFile(fileName);

        List<Thread> actorsList = new ArrayList<>();

        Collections.addAll(actorsList,
                new Thread(new Actor("Chandler", phrase)),
                new Thread(new Actor("Joey", phrase)),
                new Thread(new Actor("Monica", phrase)),
                new Thread(new Actor("Phoebe", phrase)),
                new Thread(new Actor("Rachel", phrase)),
                new Thread(new Actor("Ross", phrase)));

        for (Thread actor : actorsList) {
            actor.start();
        }

    }
}
