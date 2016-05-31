package com.dodevjutsu.katas.ohce;

public class DayPeriodGreetingSelector implements GreetingsSelector {
    private final Clock clock;

    public DayPeriodGreetingSelector(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String greetingFor(String userName) {
        int hour = clock.hour();
        if(6 <= hour && hour < 12)
        {
            return String.format("¡Buenos días %s!", userName);
        } else if(12 <= hour && hour < 20) {
            return String.format("¡Buenos tardes %s!", userName);
        }

        return String.format("¡Buenos noches %s!", userName);
    }
}
