package com.dodevjutsu.katas.ohce;

public class Ohce {
    private String userName;
    private GreetingsSelector greetingsSelector;
    private PhraseReader phraseReader;
    private Notifier notifier;

    public Ohce(String userName, Console console, Clock clock) {
    }

    public Ohce(String userName, GreetingsSelector greetingsSelector, PhraseReader phraseReader, Notifier notifier) {

        this.userName = userName;
        this.greetingsSelector = greetingsSelector;
        this.phraseReader = phraseReader;
        this.notifier = notifier;
    }

    public void run() {
        notifier.greetUser(greetingsSelector.greetingFor(userName));

        Phrase input = phraseReader.read();

        Phrase reversed = input.reverse();

        notifier.notifyReversedPhrase(reversed);

        if(input.equals(new Phrase("larutanatural"))) {
            notifier.notifyPalindromesRock();
        }
    }
}
