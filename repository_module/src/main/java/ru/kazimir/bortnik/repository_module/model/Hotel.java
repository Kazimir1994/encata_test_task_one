package ru.kazimir.bortnik.repository_module.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document(collection = "hotels")
public class Hotel {
    @Id
    private String id;
    @DBRef(lazy = true)
    private List<Room> rooms = new ArrayList<>();
    private Map<DayOfWeek, WorkingHours> scheduleWorking = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Map<DayOfWeek, WorkingHours> getScheduleWorking() {
        return scheduleWorking;
    }

    public void setScheduleWorking(Map<DayOfWeek, WorkingHours> scheduleWorking) {
        this.scheduleWorking = scheduleWorking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) &&
                Objects.equals(rooms, hotel.rooms) &&
                Objects.equals(scheduleWorking, hotel.scheduleWorking);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rooms, scheduleWorking);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", rooms=" + rooms.toString() +
                ", scheduleWorking=" + scheduleWorking +
                '}';
    }
}
