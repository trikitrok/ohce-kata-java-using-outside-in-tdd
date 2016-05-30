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

        notifier.notifyReversedPhrase(input.reverse());

        if(input.isPalindrome()) {
            notifier.notifyPalindromesRock();
        }

        input = phraseReader.read();

        if(shouldStop(input)) {
            notifier.sayByeTo(userName);
        }
    }

    private boolean shouldStop(Phrase input) {
        return input.equals(new Phrase("Stop!"));
    }
}
