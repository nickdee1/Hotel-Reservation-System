package cz.cvut.fel.rsp.server.Model;

import cz.cvut.fel.rsp.server.Model.Enums.MoneyTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Room")
public class Room extends AbstractEntity {
    @NotNull
    private String roomNumber;
    @NotNull
    private Integer capacity;
    @NotNull
    private Integer pricePerNight;
    @NotNull
    private MoneyTypeEnum moneyType;
    @Lob
    private String description;
    @NotNull
    private Boolean occupied;

    private String actualReservationNum;

    private boolean photoAdress;

    @ManyToMany
    private List<Reservation> reservations;
    @ManyToOne
    private Hotel hotel;

    public Room() {
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Integer pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public String getActualReservationNum() {
        return actualReservationNum;
    }

    public void setActualReservationNum(String actualReservationNum) {
        this.actualReservationNum = actualReservationNum;
    }

    public boolean isPhotoAdress() {
        return photoAdress;
    }

    public void setPhotoAdress(boolean photoAdress) {
        this.photoAdress = photoAdress;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public MoneyTypeEnum getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyTypeEnum moneyType) {
        this.moneyType = moneyType;
    }
}
