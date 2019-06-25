package ru.kazimir.bortnik.repository_module.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Integer numberOfBeds;
    private BigDecimal price;
    private Set<Booking> bookingList = new HashSet<>();

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(Set<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(name, room.name) &&
                Objects.equals(numberOfBeds, room.numberOfBeds) &&
                Objects.equals(price, room.price) &&
                Objects.equals(bookingList, room.bookingList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, numberOfBeds, price, bookingList);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                ", bookingList=" + bookingList +
                '}';
    }
}
