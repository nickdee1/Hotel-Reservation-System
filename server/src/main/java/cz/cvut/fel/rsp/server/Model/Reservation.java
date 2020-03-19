package cz.cvut.fel.rsp.server.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;
import cz.cvut.fel.rsp.server.Model.Enums.MoneyTypeEnum;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Reservation")
public class Reservation extends AbstractEntity {
    @NotNull
    private String reservationNumber;
    @NotNull
    private Date createdDate;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    private Integer fullPrice;
    @NotNull
    private MoneyTypeEnum moneyType;
    @NotNull
    private Boolean paied;
    @NotNull
    private Boolean guestArrived;
    @NotNull
    private Boolean guestLeft;

    @ManyToMany
    @JsonIgnore
    private List<Room> rooms;
    @ManyToOne
    @JsonBackReference(value="regUserRes")
    private User regUser;
    @ManyToOne
    @JsonBackReference(value="unregUserRes")
    private UnregisteredUser unregUser;
    @ManyToOne
    @JsonBackReference(value="hotelRes")
    private Hotel hotel;

    public Reservation() {
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice) {
        this.fullPrice = fullPrice;
    }

    public MoneyTypeEnum getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyTypeEnum moneyType) {
        this.moneyType = moneyType;
    }

    public boolean isPaied() {
        return paied;
    }

    public void setPaied(boolean paied) {
        this.paied = paied;
    }

    public boolean isGuestArrived() {
        return guestArrived;
    }

    public void setGuestArrived(boolean guestArrived) {
        this.guestArrived = guestArrived;
    }

    public boolean isGuestLeft() {
        return guestLeft;
    }

    public void setGuestLeft(boolean guestLeft) {
        this.guestLeft = guestLeft;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getRegUser() {
        return regUser;
    }

    public void setRegUser(User regUser) {
        this.regUser = regUser;
    }

    public UnregisteredUser getUnregUser() {
        return unregUser;
    }

    public void setUnregUser(UnregisteredUser unregUser) {
        this.unregUser = unregUser;
    }
    
    
}
