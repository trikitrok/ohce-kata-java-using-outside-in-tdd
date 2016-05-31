package com.dodevjutsu.katas.ohce;

import com.dodevjutsu.katas.ohce.adapters.greetings_selectors.DayPeriodGreetingSelector;
import com.dodevjutsu.katas.ohce.adapters.notifiers.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.adapters.phrase_readers.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.core.Ohce;
import com.dodevjutsu.katas.ohce.infrastructure.clock.SystemClock;
import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import com.dodevjutsu.katas.ohce.infrastructure.console.SystemConsole;

public class Main {

    public static void main(String[] args) {
        String userName = args[0];
        String stopPhraseContent = "Stop!";
        Console console = new SystemConsole();
        Ohce ohce = new Ohce(
            stopPhraseContent,
            new DayPeriodGreetingSelector(new SystemClock()),
            new ConsolePhraseReader(console),
            new ConsoleNotifier(console)
        );

        ohce.run(userName);
    }

}
