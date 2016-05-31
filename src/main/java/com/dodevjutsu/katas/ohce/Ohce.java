package com.dodevjutsu.katas.ohce;

public class Ohce {
    private String userName;
    private final Phrase stopPhrase;
    private GreetingsSelector greetingsSelector;
    private PhraseReader phraseReader;
    private Notifier notifier;

    public Ohce(String userName, String stopPhraseContent, GreetingsSelector greetingsSelector, PhraseReader phraseReader, Notifier notifier) {
        this.userName = userName;
        this.stopPhrase = new Phrase(stopPhraseContent);
        this.greetingsSelector = greetingsSelector;
        this.phraseReader = phraseReader;
        this.notifier = notifier;
    }

    public void run() {
        greetUser();
        reversePhrases();
        sayByeToUser();
    }

    private void sayByeToUser() {
        notifier.sayByeTo(userName);
    }

    private void greetUser() {
        notifier.greetUser(greetingsSelector.greetingFor(userName));
    }

    private void reversePhrases() {
        Phrase input = phraseReader.read();

        if (shouldStop(input)) {
            return;
        }

        notifier.notifyReversedPhrase(input.reverse());

        if (input.isPalindrome()) {
            notifier.notifyPalindromesRock();
        }

        reversePhrases();
    }

    private boolean shouldStop(Phrase input) {
        return input.equals(stopPhrase);
    }
}
