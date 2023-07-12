package ru.aton.multithreading;

public class Actor implements Runnable {

    private String name;
    private Phrase phrase;

    public Actor(String name, Phrase phrase) {
        this.name = name;
        this.phrase = phrase;
    }


    @Override
    public void run() {

        phrase.readPhrase(name);

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    private String getNextPhrase() {
        return "";
    }
}
