package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import com.dodevjutsu.katas.ohce.adapters.phrase_readers.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.PhraseReader;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsolePhraseReaderTest {

    @Test
    public void reads_phrases_from_the_console() {
        Mockery context = new Mockery();
        Console console = context.mock(Console.class);
        PhraseReader reader = new ConsolePhraseReader(console);

        String input = "sth";
        context.checking(new Expectations() {{
            oneOf(console).read();
            will(returnValue(input));
        }});

        assertThat(reader.read(), is(new Phrase(input)));

        context.assertIsSatisfied();
    }
}
