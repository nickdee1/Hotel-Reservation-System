package cz.cvut.fel.rsp.server.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="UnregisteredUser")
public class UnregisteredUser extends AbstractUser {
    @OneToMany
    private List<Reservation> reservations;

    public UnregisteredUser() {
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
