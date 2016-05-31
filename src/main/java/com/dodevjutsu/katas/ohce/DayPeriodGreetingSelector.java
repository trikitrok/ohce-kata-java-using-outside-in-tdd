package com.dodevjutsu.katas.ohce;

public class DayPeriodGreetingSelector implements GreetingsSelector {
    private final Clock clock;

    public DayPeriodGreetingSelector(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String greetingFor(String userName) {
        clock.hour();
        return "¡Buenos días Juanito!";
    }
}
