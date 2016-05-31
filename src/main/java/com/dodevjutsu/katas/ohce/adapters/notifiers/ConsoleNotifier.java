package com.dodevjutsu.katas.ohce.adapters.notifiers;

import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.infrastructure.Console;

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
        console.print("¡Bonita palabra!");
    }

    @Override
    public void sayByeTo(String userName) {
        console.print("Adios " + userName);
    }
}
