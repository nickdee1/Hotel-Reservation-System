package cz.cvut.fel.rsp.server.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    protected Integer id;

    public AbstractEntity() {
    }

    public Integer getId() {
        return id;
    }
}
