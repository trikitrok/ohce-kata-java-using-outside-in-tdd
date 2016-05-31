package com.dodevjutsu.katas.ohce;

public class DayPeriodGreetingSelector implements GreetingsSelector {
    private final Clock clock;

    public DayPeriodGreetingSelector(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String greetingFor(String userName) {
        DayPeriod dayPeriod = dayPeriod();
        return String.format(dayPeriod.greetingFormat(), userName);
    }

    private DayPeriod dayPeriod() {
        return DayPeriod.at(clock.hour());
    }

    private enum DayPeriod {
        MORNING{
            @Override
            public String greetingFormat() {
                return "¡Buenos días %s!";
            }
        }, AFTERNOON {
            @Override
            public String greetingFormat() {
                return "¡Buenos tardes %s!";
            }
        }, NIGHT{
            @Override
            public String greetingFormat() {
                return "¡Buenos noches %s!";
            }
        };

        public static DayPeriod at(int hour) {
            if (6 <= hour && hour < 12) {
                return MORNING;
            } else if (12 <= hour && hour < 20) {
                return AFTERNOON;
            }
            return NIGHT;
        }

        abstract public String greetingFormat();
    }
}
