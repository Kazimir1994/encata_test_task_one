package ru.kazimir.bortnik.service_module.model;

import ru.kazimir.bortnik.repository_module.model.WorkingHours;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDTO {
    private String id;
    private List<RoomDTO> rooms = new ArrayList<>();
    private Map<DayOfWeek, WorkingHours> scheduleWorking = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public Map<DayOfWeek, WorkingHours> getScheduleWorking() {
        return scheduleWorking;
    }

    public void setScheduleWorking(Map<DayOfWeek, WorkingHours> scheduleWorking) {
        this.scheduleWorking = scheduleWorking;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "id='" + id + '\'' +
                ", rooms=" + rooms +
                ", scheduleWorking=" + scheduleWorking +
                '}';
    }
}
