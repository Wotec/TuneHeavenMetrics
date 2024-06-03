package com.tuneheaven.metrics.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateService {
    private final static Logger LOG = LoggerFactory.getLogger(DateService.class);
    private final Clock clock;

    public DateService(Clock clock) {
        this.clock = clock;
    }

    public List<DateRange> getPreviousThreeFullMonthsDateRanges() {
        var currentMoth = YearMonth.now(clock);
        var previousThreeMonthStartEndDates = new ArrayList<DateRange>();

        for (int i = 1; i <= 3; i++) {
            var previousMonth = currentMoth.minusMonths(i);
            var startDate = previousMonth.atDay(1);
            var endDate = previousMonth.atEndOfMonth();
            LOG.debug("Calculated date range: startDate={}, endDate={}", startDate, endDate);

            previousThreeMonthStartEndDates.add(new DateRange(startDate, endDate));
        }

        return previousThreeMonthStartEndDates;
    }

}
