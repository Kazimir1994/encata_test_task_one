package ru.kazimir.bortnik.service_module.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

public class RoomDTO {
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Integer numberOfBeds;
    @NotNull
    @Pattern(regexp = "^(\\d{1,6}\\.)?\\d{1,6}$")
    private String price;
    private Set<BookingDTO> bookingList = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<BookingDTO> getBookingList() {
        return bookingList;
    }

    public void setBookingList(Set<BookingDTO> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                ", bookingList=" + bookingList +
                '}';
    }
}
