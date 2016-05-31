package com.dodevjutsu.katas.ohce;

public class ConsoleNotifier implements Notifier {
    private final Console console;

    public ConsoleNotifier(Console console) {
        this.console = console;
    }

    @Override
    public void greetUser(String greeting) {
        console.print(greeting);
    }

    @Override
    public void notifyReversedPhrase(Phrase phrase) {
        console.print(phrase.content());
    }

    @Override
    public void notifyPalindromesRock() {

    }

    @Override
    public void sayByeTo(String userName) {

    }
}
