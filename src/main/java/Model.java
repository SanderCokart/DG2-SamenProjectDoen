package main.java;

import java.util.Random;

public class Model {

    private String[] getWordsArray() {
        return new String[]{
                "appel", "banaan", "pelikaan", "auto", "laptop", "beeldscherm", "toetsenbord", "natuur", "lampen",
                "jassen", "adapter", "lichtbron", "bankstel", "stekker", "oordopjes", "koptelefoon", "kruk", "bar",
                "games", "muis", "plafonniere", "wieldopjesfabriek", "autodrop", "galgje", "internet", "desktop",
                "monitor", "oerwoud", "dinosaurus", "programmeur"
        };
    }

    public String getRandomWord() {
        Random rand = new Random();

        // Obtain a number between [0 - 30].
        int randomNumber = rand.nextInt(30);

        String[] words = getWordsArray();

        return words[randomNumber];
    }

}
