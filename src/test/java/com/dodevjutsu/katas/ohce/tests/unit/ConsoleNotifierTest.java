package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.Console;
import com.dodevjutsu.katas.ohce.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.Notifier;
import com.dodevjutsu.katas.ohce.Phrase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class ConsoleNotifierTest {

    private Mockery context;
    private Console console;
    private Notifier notifier;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        console = context.mock(Console.class);
        notifier = new ConsoleNotifier(console);
    }

    @Test
    public void notifies_reversed_phrases() {
        context.checking(new Expectations() {{
            oneOf(console).print("oto");
        }});

        notifier.notifyReversedPhrase(new Phrase("oto"));

        context.assertIsSatisfied();
    }

    @Test
    public void greets_the_user() {
        final String anyGreeting = "sth";
        context.checking(new Expectations() {{
            oneOf(console).print(anyGreeting);
        }});

        notifier.greetUser(anyGreeting);

        context.assertIsSatisfied();
    }
}
