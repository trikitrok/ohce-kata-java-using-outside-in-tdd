package com.dodevjutsu.katas.ohce.tests.acceptance;

import com.dodevjutsu.katas.ohce.adapters.greetings_selectors.DayPeriodGreetingSelector;
import com.dodevjutsu.katas.ohce.adapters.notifiers.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.adapters.phrase_readers.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.core.Ohce;
import com.dodevjutsu.katas.ohce.infrastructure.clock.Clock;
import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class RunningOhceOnconsoleTest {

    private Mockery context;
    private Console console;
    private Clock clock;
    private Ohce ohce;
    private String stopPhraseContent;

    @Before
    public void setUp() {
        context = new Mockery();
        console = context.mock(Console.class);
        clock = context.mock(Clock.class);
        stopPhraseContent = "Stop!";
        ohce = new Ohce(
            stopPhraseContent,
            new DayPeriodGreetingSelector(clock),
            new ConsolePhraseReader(console),
            new ConsoleNotifier(console)
        );
    }

    @Test
    public void running_during_the_morning() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(10));

            exactly(4).of(console).read();
            will(onConsecutiveCalls(
                returnValue("hola"),
                returnValue("oto"),
                returnValue("stop"),
                returnValue("Stop!")
            ));

            oneOf(console).print("¡Buenos días Pedro!");
            oneOf(console).print("aloh");
            oneOf(console).print("oto");
            oneOf(console).print("¡Bonita palabra!");
            oneOf(console).print("pots");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run("Pedro");

        context.assertIsSatisfied();
    }

    @Test
    public void running_during_the_afternoon() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(16));

            oneOf(console).read();
            will(returnValue("Stop!"));

            oneOf(console).print("¡Buenas tardes Pedro!");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run("Pedro");

        context.assertIsSatisfied();
    }

    @Test
    public void running_during_the_night() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(23));

            oneOf(console).read();
            will(returnValue("Stop!"));

            oneOf(console).print("¡Buenas noches Pedro!");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run("Pedro");

        context.assertIsSatisfied();
    }
}
