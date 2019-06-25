package ru.kazimir.bortnik.service_module.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class BookingDTO {
    private String id;
    private UserDTO userDTO;
    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    private String roomId;
    @NotNull
    private LocalDateTime from;
    @NotNull
    private LocalDateTime to;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", userDTO=" + userDTO +
                ", roomId='" + roomId + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
