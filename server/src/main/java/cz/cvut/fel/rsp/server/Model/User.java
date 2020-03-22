package cz.cvut.fel.rsp.server.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.cvut.fel.rsp.server.Model.Enums.UserRoleEnum;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.PostRemove;
import javax.persistence.PreRemove;
import org.eclipse.persistence.annotations.DeleteAll;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "registeredUser")
public class User extends AbstractUser {
    @NotNull
    private String password;
    @NotNull
    private Boolean blocked;
    @NotNull
    private List<UserRoleEnum> roles;

    @OneToMany
    @JsonManagedReference(value = "regUserRes")
    private List<Reservation> reservations;
    @ManyToOne
    @JsonBackReference(value="hotelUser")
    private Hotel hotel;


    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
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
    
    public boolean isDeleteable() {
        if(getReservations().isEmpty()) {
            return true;
        }
        Date last = getReservations().get(0).getCreatedDate();
        for(Reservation r: getReservations()) {
            if(r.getCreatedDate().after(last)) {
                last = r.getCreatedDate();
            }
        }
        Calendar now =Calendar.getInstance();
        Date nowDate = new Date(System.currentTimeMillis());
        now.setTime(nowDate);
        Calendar lastCal = Calendar.getInstance();
        lastCal.setTime(last);
        if(now.get(Calendar.YEAR)  - lastCal.get(Calendar.YEAR) > 6) {
            return true;
        }
        return false;
    }
}
