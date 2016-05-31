package com.dodevjutsu.katas.ohce;

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
