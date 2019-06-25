package ru.kazimir.bortnik.service_module.model;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class UpdateWorkingHoursDTO {
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalTime from;
    @NotNull
    private LocalTime to;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "UpdateWorkingHoursDTO{" +
                "dayOfWeek=" + dayOfWeek +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
