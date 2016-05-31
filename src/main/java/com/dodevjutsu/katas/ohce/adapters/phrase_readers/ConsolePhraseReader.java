package com.dodevjutsu.katas.ohce.adapters.phrase_readers;

import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.PhraseReader;
import com.dodevjutsu.katas.ohce.infrastructure.Console;

public class ConsolePhraseReader implements PhraseReader {
    private final Console console;

    public ConsolePhraseReader(Console console) {
        this.console = console;
    }

    @Override
    public Phrase read() {
        return new Phrase(console.read());
    }
}
