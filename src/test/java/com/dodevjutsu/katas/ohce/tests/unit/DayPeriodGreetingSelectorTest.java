package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.infrastructure.Clock;
import com.dodevjutsu.katas.ohce.adapters.greetings_selectors.DayPeriodGreetingSelector;
import com.dodevjutsu.katas.ohce.core.GreetingsSelector;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DayPeriodGreetingSelectorTest {

    private Mockery context;
    private Clock clock;
    private GreetingsSelector greetingsSelector;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        clock = context.mock(Clock.class);
        greetingsSelector = new DayPeriodGreetingSelector(clock);
    }

    @Test
    public void selects_morning_greetings() {
        context.checking(new Expectations() {{
            exactly(3).of(clock).hour();
            will(onConsecutiveCalls(
                returnValue(6),
                returnValue(10),
                returnValue(11)
            ));
        }});

        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos días Juanito!"));
        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos días Juanito!"));
        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos días Juanito!"));

        context.assertIsSatisfied();
    }

    @Test
    public void selects_afternoon_greetings() {
        context.checking(new Expectations() {{
            exactly(3).of(clock).hour();
            will(onConsecutiveCalls(
                returnValue(12),
                returnValue(16),
                returnValue(19)
            ));
        }});

        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos tardes Juanito!"));
        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos tardes Juanito!"));
        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos tardes Juanito!"));

        context.assertIsSatisfied();
    }

    @Test
    public void selects_night_greetings() {
        context.checking(new Expectations() {{
            exactly(3).of(clock).hour();
            will(onConsecutiveCalls(
                returnValue(20),
                returnValue(23),
                returnValue(5)
            ));
        }});

        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos noches Juanito!"));
        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos noches Juanito!"));
        assertThat(greetingsSelector.greetingFor("Juanito"), is("¡Buenos noches Juanito!"));

        context.assertIsSatisfied();
    }
}
