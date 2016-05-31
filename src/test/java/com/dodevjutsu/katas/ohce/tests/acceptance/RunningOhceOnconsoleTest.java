package com.dodevjutsu.katas.ohce.tests.acceptance;

import com.dodevjutsu.katas.ohce.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class RunningOhceOnconsoleTest {

    private Mockery context;
    private Console console;
    private Clock clock;
    private Ohce ohce;

    @Before
    public void setUp() {
        context = new Mockery();
        console = context.mock(Console.class);
        clock = context.mock(Clock.class);
        ohce = new Ohce(
            "Pedro",
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

        ohce.run();

        context.assertIsSatisfied();
    }

    @Test
    public void running_during_the_afternoon() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(16));

            oneOf(console).read();
            will(returnValue("Stop!"));

            oneOf(console).print("¡Buenos tardes Pedro!");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run();

        context.assertIsSatisfied();
    }

    @Test
    public void running_during_the_night() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(23));

            oneOf(console).read();
            will(returnValue("Stop!"));

            oneOf(console).print("¡Buenos noches Pedro!");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run();

        context.assertIsSatisfied();
    }
}
