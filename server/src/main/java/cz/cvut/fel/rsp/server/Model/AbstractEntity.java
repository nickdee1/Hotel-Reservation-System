package cz.cvut.fel.rsp.server.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private Long id;

    public AbstractEntity() {
    }

    public Long getId() {
        return id;
    }
}
