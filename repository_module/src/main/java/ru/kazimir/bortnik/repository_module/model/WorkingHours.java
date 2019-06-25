package ru.kazimir.bortnik.repository_module.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Objects;

@Document
public class WorkingHours {
    private LocalTime from;
    private LocalTime to;

    public WorkingHours(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingHours that = (WorkingHours) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
