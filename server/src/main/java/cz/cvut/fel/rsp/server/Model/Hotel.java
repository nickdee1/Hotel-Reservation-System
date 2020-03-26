package cz.cvut.fel.rsp.server.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Hotel")
public class Hotel extends AbstractEntity {
    @NotNull
    private String name;
    @NotNull
    private Integer capacity;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String streetName;
    @NotNull
    private Integer postalCode;
    @NotNull
    private String email;
    @NotNull
    private String phoneNum;

    @OneToMany
    @JsonManagedReference(value="hotelRoom")
    private List<Room> rooms;
    @OneToMany
    @JsonManagedReference(value = "hotelUser")
    private List<User> registeredUsers;
    @OneToMany
    @JsonManagedReference(value = "hotelRes")
    private List<Reservation> reservations;

    public Hotel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Room> getRooms() {
        if(rooms == null) {
            rooms = new ArrayList();
        }
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<User> getRegisteredUsers() {
        if(registeredUsers == null) {
            registeredUsers = new ArrayList();
        }
        return registeredUsers;
    }

    public void setRegisteredUsers(List<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public List<Reservation> getReservations() {
        if(reservations == null) {
            reservations = new ArrayList();
        }
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    public void setId(int id) {
        this.id =id;
    }


    
    
}
