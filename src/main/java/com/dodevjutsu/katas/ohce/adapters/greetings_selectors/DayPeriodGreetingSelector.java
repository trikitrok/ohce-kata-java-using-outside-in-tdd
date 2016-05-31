package com.dodevjutsu.katas.ohce.adapters.greetings_selectors;

import com.dodevjutsu.katas.ohce.core.GreetingsSelector;
import com.dodevjutsu.katas.ohce.infrastructure.clock.Clock;

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
        MORNING {
            @Override
            public String greetingFormat() {
                return "¡Buenos días %s!";
            }
        }, AFTERNOON {
            @Override
            public String greetingFormat() {
                return "¡Buenos tardes %s!";
            }
        }, NIGHT {
            @Override
            public String greetingFormat() {
                return "¡Buenos noches %s!";
            }
        };

        public static DayPeriod at(int hour) {
            if (isDuringMorning(hour)) {
                return MORNING;
            } else if (isDuringAfternoon(hour)) {
                return AFTERNOON;
            }
            return NIGHT;
        }

        private static boolean isDuringAfternoon(int hour) {
            return 12 <= hour && hour < 20;
        }

        private static boolean isDuringMorning(int hour) {
            return 6 <= hour && hour < 12;
        }

        abstract public String greetingFormat();
    }
}
