package cz.cvut.fel.rsp.server.Model;

import cz.cvut.fel.rsp.server.Model.Enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "registeredUser")
public class User extends AbstractUser {
    @NotNull
    private String password;
    @NotNull
    private boolean blocked;
    @NotNull
    private List<UserRoleEnum> roles;

    @OneToMany
    private List<Reservation> reservations;
    @ManyToOne
    private Hotel hotel;


    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
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
}
