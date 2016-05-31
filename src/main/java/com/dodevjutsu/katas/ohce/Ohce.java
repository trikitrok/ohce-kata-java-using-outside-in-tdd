package com.dodevjutsu.katas.ohce;

public class Ohce {
    private final Phrase stopPhrase;
    private GreetingsSelector greetingsSelector;
    private PhraseReader phraseReader;
    private Notifier notifier;

    public Ohce(
        String stopPhraseContent,
        GreetingsSelector greetingsSelector,
        PhraseReader phraseReader,
        Notifier notifier
    ) {
        this.stopPhrase = new Phrase(stopPhraseContent);
        this.greetingsSelector = greetingsSelector;
        this.phraseReader = phraseReader;
        this.notifier = notifier;
    }

    public void run(String userName) {
        greetUser(userName);
        reversePhrases();
        sayByeToUser(userName);
    }

    private void sayByeToUser(String userName) {
        notifier.sayByeTo(userName);
    }

    private void greetUser(String userName) {
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
