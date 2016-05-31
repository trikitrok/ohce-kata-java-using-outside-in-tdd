package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.Clock;
import com.dodevjutsu.katas.ohce.DayPeriodGreetingSelector;
import com.dodevjutsu.katas.ohce.GreetingsSelector;
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
    public void selects_morning_greetings(){
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(onConsecutiveCalls(
                returnValue(6)
            ));
        }});

        assertThat(
            greetingsSelector.greetingFor("Juanito"),
            is("¡Buenos días Juanito!")
        );

        context.assertIsSatisfied();
    }
}
