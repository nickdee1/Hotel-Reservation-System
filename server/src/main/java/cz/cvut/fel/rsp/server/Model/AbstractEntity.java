package cz.cvut.fel.rsp.server.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public AbstractEntity() {
    }

    public Long getId() {
        return id;
    }
}
