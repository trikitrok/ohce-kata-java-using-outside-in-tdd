package com.dodevjutsu.katas.ohce.infrastructure.clock;

import java.util.Calendar;

public class SystemClock implements Clock {
    @Override
    public int hour() {
        return 1 + Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
}
