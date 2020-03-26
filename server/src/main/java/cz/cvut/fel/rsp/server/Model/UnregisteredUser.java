package cz.cvut.fel.rsp.server.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.persistence.CascadeType;

@Entity
@Table(name="UnregisteredUser")
public class UnregisteredUser extends AbstractUser {
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "unregUserRes")
    private List<Reservation> reservations;

    public UnregisteredUser() {
        reservations = new ArrayList<>();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setReservation(Reservation reservation) { reservations.add(reservation); }

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
